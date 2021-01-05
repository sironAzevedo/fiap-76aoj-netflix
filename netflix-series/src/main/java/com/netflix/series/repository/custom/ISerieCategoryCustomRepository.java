package com.netflix.series.repository.custom;

import java.util.List;

import com.netflix.series.model.dto.TopSerieCategoryDTO;

public interface ISerieCategoryCustomRepository {

	List<TopSerieCategoryDTO> getTopSerieByCategory();
}
