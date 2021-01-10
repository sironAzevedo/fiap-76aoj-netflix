package com.netflix.series.service.impl;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netflix.series.converter.SerieConverter;
import com.netflix.series.feignClients.CategoryClient;
import com.netflix.series.feignClients.UserClient;
import com.netflix.series.handler.exception.NotFoundException;
import com.netflix.series.handler.exception.UserException;
import com.netflix.series.kafka.producer.SerieWatchedProducer;
import com.netflix.series.model.SerieCategoryEntity;
import com.netflix.series.model.SerieEntity;
import com.netflix.series.model.SerieKeyWordEntity;
import com.netflix.series.model.dto.SerieDTO;
import com.netflix.series.model.dto.SerieLikeDTO;
import com.netflix.series.model.dto.SerieUserDTO;
import com.netflix.series.model.dto.SerieWatchedDTO;
import com.netflix.series.model.dto.TopSerieCategoryDTO;
import com.netflix.series.model.dto.TopSerieCategoryResponseDTO;
import com.netflix.series.model.dto.TopSerieDTO;
import com.netflix.series.model.dto.UserDTO;
import com.netflix.series.repository.ISerieCategoryRepository;
import com.netflix.series.repository.ISerieKeywordsRepository;
import com.netflix.series.repository.ISerieLikeRepository;
import com.netflix.series.repository.ISerieRepository;
import com.netflix.series.repository.ISerieWatchFutureRepository;
import com.netflix.series.repository.ISerieWatchedRepository;
import com.netflix.series.repository.custom.ISerieCategoryCustomRepository;
import com.netflix.series.service.ISerieService;

@Service
public class SerieServiceImpl implements ISerieService {

	@Autowired
	private ISerieRepository repo;
	
	@Autowired
	private ISerieCategoryRepository mcRepo;
	
	@Autowired
	private ISerieKeywordsRepository keyRepo;
	
	@Autowired
	private SerieConverter convert;
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private ISerieCategoryCustomRepository mcCustomRepo;
	
	@Autowired
	private ISerieLikeRepository serieLikeRepo;
	
	@Autowired
	private CategoryClient categoryClient;
	
	@Autowired
	private ISerieWatchFutureRepository watchFuture;
	
	@Autowired
    private ISerieWatchedRepository serieWatchedRepo;
	
	@Autowired
	private SerieWatchedProducer producer;

	@Override
	public void create(SerieDTO dto) {
		SerieEntity serie = repo.save(convert.toEntity(dto));
		dto.getKeywords().stream()
		.filter(i1 -> {//Esse filtro estar removendo as palavras da lista de palavras do DTO que já estão cadastrada no banco de dados para a serie
			return !keyRepo.findBySerie(serie).stream()
					.map(SerieKeyWordEntity::getKeyword)
					.anyMatch(i2 -> i2.equalsIgnoreCase(i1));
		})
		.forEach(w -> {
			SerieKeyWordEntity key = new SerieKeyWordEntity();
			key.setKeyword(w);
			key.setSerie(serie);
			keyRepo.save(key);
		});
	
		dto.getCategories().stream()
		.filter(i1 -> {//Esse filtro estar removendo as categorias da lista de categorias do DTO que já estão cadastrada no banco de dados para a serie
			return !mcRepo.findBySerie(serie).stream()
					.map(SerieCategoryEntity::getCategory)
					.anyMatch(i2 -> i2.equals(Long.valueOf(i1)));
		})
		.forEach(c -> {
			Optional.ofNullable(categoryClient.category(Long.valueOf(c))).ifPresent(r -> {
				SerieCategoryEntity mc = new SerieCategoryEntity();
				mc.setSerie(serie);
				mc.setCategory(r.getId());
				mcRepo.save(mc);
			});
		});
	}

	@Override
	public Page<SerieDTO> findAll(Pageable pageable) {
		return repo.findAll(pageable).map(convert::toDTO);
	}
	
	@Override
	public SerieDTO detail(Long id_Serie) {
		return repo.findById(id_Serie).map(convert::toDetail).get();
	}
	
	@Override
	public Page<SerieDTO> byCategory(Long idCategory, Pageable pageable) {
		List<SerieDTO> series = repo.findByCategoriesCategory(idCategory).stream().map(convert::toDTO).collect(Collectors.toList());
		return new PageImpl<>(series, pageable, series.size());
	}

	@Override
	public Page<SerieDTO> getKeyword(String word, Pageable pageable) {
		final String[] queries = word.split(" ");
		List<SerieDTO> Series = new ArrayList<>();
		
		Arrays.stream(queries)
			.forEach(w -> Series.addAll(findBykeywords(w)));
		
		List<SerieDTO> result = Series.stream()
				.collect(
						collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
								comparingLong(SerieDTO::getId))), ArrayList::new));
		
		return new PageImpl<>(result, pageable, result.size());
	}
	
	@Override
	public void watched(SerieWatchedDTO dto) {
		findById(dto.getSerie().getId());
		serieWatchedRepo.save(convert.toSerieWatched(dto, getUser(dto.getUser()).getId()));
		producer.sendMessage(dto);
	}

	@Override
	public Page<SerieWatchedDTO> watched(String user, Pageable pageable) {
		List<SerieWatchedDTO> series = new ArrayList<>();
		serieWatchedRepo.findByUser(getUser(user).getId()).forEach(mc -> series.add(convert.toDTO(mc)));
		return new PageImpl<>(series, pageable, series.size());
	}
	
	@Override
	public void like(SerieLikeDTO dto) {
		findById(dto.getSerie().getId());
		serieLikeRepo.save(convert.toSerieLike(dto, getUser(dto.getUser()).getId()));
	}

	@Override
	public Page<SerieLikeDTO> likes(String user, Pageable pageable) {
		List<SerieLikeDTO> series = new ArrayList<>();
		serieLikeRepo.findByUser(getUser(user).getId()).forEach(ml -> series.add(convert.toDTO(ml)));
		return new PageImpl<>(series, pageable, series.size());
	}
	
	@Override
	public void watchFuture(String user, Long Serie) {
		watchFuture.save(convert.toSerieWatchFuture(findById(Serie), getUser(user).getId()));
	}
	
	@Override
	public Page<SerieUserDTO> watchFuture(String user, Pageable pageable) {
		List<SerieUserDTO> series = new ArrayList<>();
		watchFuture.findByUser(getUser(user).getId()).forEach(wf -> series.add(convert.toDTO(wf)));
		return new PageImpl<>(series, pageable, series.size());
	}
	
	@Override
	public void deleteWatchFuture(String user, Long serie) {
		watchFuture.findByUserAndSerie(getUser(user).getId(), findById(serie)).ifPresent(r -> watchFuture.delete(r));
	}
	
	@Override
	public List<TopSerieCategoryResponseDTO> getTopSerieByCategory(Pageable pageable) {
		List<TopSerieCategoryResponseDTO> resp = new ArrayList<>();
		List<TopSerieCategoryDTO> series = mcCustomRepo.getTopSerieByCategory().stream()
		.collect(
				collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
						comparingLong(TopSerieCategoryDTO::getCodSerie))), ArrayList::new));
		
		
		
		series.stream().collect(Collectors.groupingBy(c -> c.getCategory())).entrySet().forEach(entry -> {
			Optional.ofNullable(categoryClient.category(entry.getKey())).ifPresent(category -> {
				TopSerieCategoryResponseDTO dtoResp = new TopSerieCategoryResponseDTO();
				dtoResp.setCategory(category.getName());
				entry.getValue().forEach(top -> {
					TopSerieDTO dto = new TopSerieDTO();
					dto.setViews(top.getAmount());
					dto.setSerie(convert.toDTO(findById(top.getCodSerie())));
					dtoResp.getSeries().add(dto);
				}); 
				resp.add(dtoResp);
			});
		});
		
		return resp;
	}
	
	private SerieEntity findById(Long Serie) {
		return repo.findById(Serie).orElseThrow(() -> new NotFoundException("Serie not found"));
	}
	
	private List<SerieDTO> findBykeywords(String word) {
		List<SerieDTO> series = new ArrayList<>();
		repo.findBykeywordsKeywordContainingIgnoreCase(word).forEach(r -> series.add(convert.toDTO(r))); 
		return series;
	}
	
	private UserDTO getUser(String user) {
		return Optional.ofNullable(userClient.findByEmail(user)).orElseThrow(() -> new UserException("User not found"));
	}
}
