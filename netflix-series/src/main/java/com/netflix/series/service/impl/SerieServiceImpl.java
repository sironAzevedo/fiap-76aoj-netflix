package com.netflix.series.service.impl;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.netflix.series.converter.SerieConverter;
import com.netflix.series.feignClients.CategoryFeignClient;
import com.netflix.series.feignClients.UserFeignClient;
import com.netflix.series.handler.exception.NotFoundException;
import com.netflix.series.handler.exception.UserException;
import com.netflix.series.kafka.producer.SerieWatchedProducer;
import com.netflix.series.model.SerieEntity;
import com.netflix.series.model.SerieLikeEntity;
import com.netflix.series.model.SerieWatchFutureEntity;
import com.netflix.series.model.SerieWatchedEntity;
import com.netflix.series.model.dto.CategoryDTO;
import com.netflix.series.model.dto.SerieDTO;
import com.netflix.series.model.dto.SerieLikeDTO;
import com.netflix.series.model.dto.SerieUserDTO;
import com.netflix.series.model.dto.SerieWatchedDTO;
import com.netflix.series.model.dto.TopSerieCategoryDTO;
import com.netflix.series.model.dto.TopSerieCategoryResponseDTO;
import com.netflix.series.model.dto.TopSerieDTO;
import com.netflix.series.model.dto.UserDTO;
import com.netflix.series.repository.ISerieCategoryRepository;
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
	private SerieConverter convert;
	
	@Autowired
	private UserFeignClient userClient;
	
	@Autowired
	private ISerieCategoryRepository mcRepo;
	
	@Autowired
	private ISerieCategoryCustomRepository mcCustomRepo;
	
	@Autowired
	private ISerieLikeRepository serieLikeRepo;
	
	@Autowired
	private CategoryFeignClient categoryClient;
	
	@Autowired
	private ISerieWatchFutureRepository watchFuture;
	
	@Autowired
    private ISerieWatchedRepository serieWatchedRepo;
	
	@Autowired
	private SerieWatchedProducer producer;
	
	@Override
	public Page<SerieDTO> byCategory(Long idCategory, Pageable pageable) {

		List<SerieDTO> Series = new ArrayList<>();
		mcRepo.getSerieByCategory(idCategory).forEach(mc -> {
			SerieDTO Serie = convert.toDTO(mc.getPk().getSerie());
			mcRepo.getCategoryBySerie(mc.getPk().getSerie()).forEach(
					cat -> Serie.getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			Series.add(Serie);
		});
		return new PageImpl<>(Series, pageable, Series.size());
	}

	@Override
	public SerieDTO detail(Long id_Serie) {
		Optional<SerieDTO> map = repo.findById(id_Serie).map(convert::toDTO);
		return map.get();
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
		SerieWatchedEntity entity = convert.toSerieWatched(dto, getUser(dto.getUser()).getId());
		serieWatchedRepo.save(entity);
		producer.sendMessage(dto);
	}

	@Override
	public Page<SerieWatchedDTO> watched(String user, Pageable pageable) {
		List<SerieWatchedDTO> Series = new ArrayList<>();
		serieWatchedRepo.findByUser(getUser(user).getId()).forEach(mc -> {
			SerieWatchedDTO Serie = convert.toDTO(mc);
			mcRepo.getCategoryBySerie(mc.getPk().getSerie()).forEach(
					cat -> Serie.getSerie().getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			Series.add(Serie);			
		});
		return new PageImpl<>(Series, pageable, Series.size());
	}
	
	@Override
	public void like(SerieLikeDTO dto) {
		findById(dto.getSerie().getId());
		SerieLikeEntity entity = convert.toSerieLike(dto, getUser(dto.getUser()).getId());
		serieLikeRepo.save(entity);
	}

	@Override
	public Page<SerieLikeDTO> likes(String user, Pageable pageable) {
		List<SerieLikeDTO> Series = new ArrayList<>();
		serieLikeRepo.findByUser(getUser(user).getId()).forEach(ml -> {
			SerieLikeDTO Serie = convert.toDTO(ml);
			mcRepo.getCategoryBySerie(ml.getPk().getSerie()).forEach(
					cat -> Serie.getSerie().getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			Series.add(Serie);			
		});
		
		return new PageImpl<>(Series, pageable, Series.size());
	}
	
	@Override
	public void watchFuture(String user, Long Serie) {
		SerieWatchFutureEntity entity = convert.toSerieWatchFuture(findById(Serie), getUser(user).getId());
		watchFuture.save(entity);
	}
	
	@Override
	public Page<SerieUserDTO> watchFuture(String user, Pageable pageable) {
		List<SerieUserDTO> Series = new ArrayList<>();
		watchFuture.findByUser(getUser(user).getId()).forEach(wf -> {
			SerieUserDTO Serie = convert.toDTO(wf);
			mcRepo.getCategoryBySerie(wf.getPk().getSerie()).forEach(
					cat -> Serie.getSerie().getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			Series.add(Serie);			
		});
		
		return new PageImpl<>(Series, pageable, Series.size());
	}
	
	@Override
	public void deleteWatchFuture(String user, Long Serie) {
		SerieWatchFutureEntity entity = convert.toSerieWatchFuture(findById(Serie), getUser(user).getId());
		Optional<SerieWatchFutureEntity> res = watchFuture.findByUserAndSerie(entity.getPk().getUser(), entity.getPk().getSerie());
		if(res.isPresent()) {
			watchFuture.delete(entity);
		}
	}
	
	@Override
	public List<TopSerieCategoryResponseDTO> getTopSerieByCategory(Pageable pageable) {
		List<TopSerieCategoryResponseDTO> resp = new ArrayList<>();
		List<TopSerieCategoryDTO> Series = mcCustomRepo.getTopSerieByCategory().stream()
		.collect(
				collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
						comparingLong(TopSerieCategoryDTO::getCodSerie))), ArrayList::new));
		
		
		Map<CategoryDTO, List<TopSerieCategoryDTO>> collect = Series.stream().collect(Collectors.groupingBy(c -> categoryClient.category(c.getCategory())));
		for (Entry<CategoryDTO, List<TopSerieCategoryDTO>> entry : collect.entrySet()) {
			TopSerieCategoryResponseDTO dtoResp = new TopSerieCategoryResponseDTO();
			dtoResp.setCategory(entry.getKey().getName());
			
			for (TopSerieCategoryDTO top : entry.getValue()) {
				TopSerieDTO dto = new TopSerieDTO();
				dto.setViews(top.getAmount());
				dto.setSerie(convert.toDTO(findById(top.getCodSerie())));
				dtoResp.getSeries().add(dto);
			}
			
			resp.add(dtoResp);
	    }
		
		return resp;
	}
	
	private SerieEntity findById(Long Serie) {
		return repo.findById(Serie).orElseThrow(() -> new NotFoundException("Serie not found"));
	}
	
	private List<SerieDTO> findBykeywords(String word) {
		List<SerieDTO> Series = new ArrayList<>();
		repo.findBykeywordsKeywordContainingIgnoreCase(word).forEach(r -> {
			SerieDTO Serie = convert.toDTO(r);
			mcRepo.getCategoryBySerie(r).forEach(cat -> Serie.getCategories().add(categoryClient.category(cat.getPk().getCategory()).getName()));
			Series.add(Serie);
		}); 
		return Series;
	}
	
	private UserDTO getUser(String user) {
		UserDTO u = userClient.findByEmail(user);
		if (u == null) {
			throw new UserException("User not found");
		}
		return u;
	}
}
