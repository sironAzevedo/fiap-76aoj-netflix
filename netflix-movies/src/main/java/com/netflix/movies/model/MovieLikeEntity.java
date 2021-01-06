package com.netflix.movies.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.netflix.movies.model.enums.MovieLikeEnum;

@Entity
@Table(name = "tb_movie_like")
public class MovieLikeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MovieUserEntityPK pk;

	@NotNull
	@Column(name = "liked")
	@Enumerated(EnumType.STRING)
	private MovieLikeEnum liked;

	public MovieLikeEntity() {
		super();
	}

	public MovieUserEntityPK getPk() {
		return pk;
	}

	public void setPk(MovieUserEntityPK pk) {
		this.pk = pk;
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
