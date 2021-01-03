package com.netflix.movies.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.netflix.movies.model.enums.MovieLikeEnum;

@JsonInclude(Include.NON_EMPTY)
public class MovieLikeDTO extends MovieUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Informe yes ou no")
	private MovieLikeEnum liked;

	public MovieLikeDTO() {
		super();
	}

	public MovieLikeEnum getLiked() {
		return liked;
	}

	public void setLiked(MovieLikeEnum liked) {
		this.liked = liked;
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
