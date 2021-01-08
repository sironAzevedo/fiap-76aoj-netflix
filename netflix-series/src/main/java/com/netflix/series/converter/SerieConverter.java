package com.netflix.series.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.series.feignClients.CategoryClient;
import com.netflix.series.model.SerieEntity;
import com.netflix.series.model.SerieKeyWordEntity;
import com.netflix.series.model.SerieLikeEntity;
import com.netflix.series.model.SerieUserEntityPK;
import com.netflix.series.model.SerieWatchFutureEntity;
import com.netflix.series.model.SerieWatchedEntity;
import com.netflix.series.model.dto.SerieDTO;
import com.netflix.series.model.dto.SerieLikeDTO;
import com.netflix.series.model.dto.SerieUserDTO;
import com.netflix.series.model.dto.SerieWatchedDTO;

@Mapper(componentModel = "spring")
public abstract class SerieConverter {
	@Autowired
	private CategoryClient categoryClient;

	public SerieDTO toDTO(SerieEntity entity) {
		SerieDTO dto = new SerieDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setSummary(entity.getSummary());
		dto.setReleaseDate(entity.getReleaseDate());
		dto.setSeason(entity.getSeason());
		dto.setKeywords(entity
				.getKeywords()
				.stream()
				.map(SerieKeyWordEntity::getKeyword)
				.collect(Collectors.toList()));
		
		
		List<String> categories = new ArrayList<>();
		entity.getCategories().forEach(c -> Optional.ofNullable(categoryClient.category(c.getCategory())).ifPresent(r -> categories.add(r.getName())));		
		dto.setCategories(categories);		
		return dto;
	}
	
	public SerieWatchedDTO toDTO(SerieWatchedEntity entity) {
		SerieWatchedDTO dto = new SerieWatchedDTO();
		dto.setSerie(this.toDTO(entity.getPk().getSerie()));
		dto.setDateWatched(entity.getDate());		
		return dto;
	}
	
	public SerieLikeDTO toDTO(SerieLikeEntity entity) {
		SerieLikeDTO dto = new SerieLikeDTO();
		dto.setSerie(this.toDTO(entity.getPk().getSerie()));
		dto.setLiked(entity.getLiked());
		return dto;
	}
	
	public SerieUserDTO toDTO(SerieWatchFutureEntity entity) {
		SerieUserDTO dto = new SerieUserDTO();
		dto.setSerie(this.toDTO(entity.getPk().getSerie()));
		return dto;
	}

	public SerieWatchedEntity toSerieWatched(SerieWatchedDTO dto, Long user) {
		SerieEntity serie = new SerieEntity();
		serie.setId(dto.getSerie().getId());
		
		SerieUserEntityPK pk = new SerieUserEntityPK();
		pk.setUser(user);
		pk.setSerie(serie);
		
		SerieWatchedEntity entity = new SerieWatchedEntity();
		entity.setPk(pk);
		entity.setDate(new Date());
		
		return entity;
	}
	
	public SerieLikeEntity toSerieLike(SerieLikeDTO dto, Long user) {
		SerieEntity serie = new SerieEntity();
		serie.setId(dto.getSerie().getId());
		
		SerieUserEntityPK pk = new SerieUserEntityPK();
		pk.setUser(user);
		pk.setSerie(serie);
		
		SerieLikeEntity entity = new SerieLikeEntity();
		entity.setPk(pk);
		entity.setLiked(dto.getLiked());
		return entity;
	}
	
	public SerieWatchFutureEntity toSerieWatchFuture(SerieEntity serie , Long user) {
		SerieUserEntityPK pk = new SerieUserEntityPK();
		pk.setUser(user);
		pk.setSerie(serie);
		
		SerieWatchFutureEntity entity = new SerieWatchFutureEntity();
		entity.setPk(pk);
		return entity;
	}
}
