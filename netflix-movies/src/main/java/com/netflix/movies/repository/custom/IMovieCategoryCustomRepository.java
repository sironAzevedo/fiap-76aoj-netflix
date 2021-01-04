package com.netflix.movies.repository.custom;

import java.util.List;

import com.netflix.movies.model.dto.TopMovieCategoryDTO;

public interface IMovieCategoryCustomRepository {

	List<TopMovieCategoryDTO> getTopMovieByCategory();
}
