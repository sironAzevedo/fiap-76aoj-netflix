package com.netflix.movies.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TopMovieCategoryResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String category;
	private List<TopMovieDTO> movies = new ArrayList<>();

	public TopMovieCategoryResponseDTO() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public List<TopMovieDTO> getMovies() {
		return movies;
	}

	public void setMovies(List<TopMovieDTO> movies) {
		this.movies = movies;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
