package com.netflix.series.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.netflix.series.model.dto.SerieDTO;
import com.netflix.series.model.dto.SerieLikeDTO;
import com.netflix.series.model.dto.SerieUserDTO;
import com.netflix.series.model.dto.SerieWatchedDTO;
import com.netflix.series.model.dto.TopSerieCategoryResponseDTO;

public interface ISerieService {

	Page<SerieDTO> byCategory(Long idCategory, Pageable pageable);

	SerieDTO detail(Long id_serie);

	Page<SerieDTO> getKeyword(String word, Pageable pageable);

	void watched(SerieWatchedDTO dto);

	Page<SerieWatchedDTO> watched(String user, Pageable pageable);

	void like(SerieLikeDTO dto);

	Page<SerieLikeDTO> likes(String user, Pageable pageable);

	void watchFuture(String user, Long serie);
	
	Page<SerieUserDTO> watchFuture(String user, Pageable pageable);	
	
	void deleteWatchFuture(String user, Long serie);
	
	List<TopSerieCategoryResponseDTO> getTopSerieByCategory(Pageable pageable);
}
