package com.netflix.movies.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "tb_movie_watch_future")
public class MovieWatchFutureEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MovieUserEntityPK pk;
	 
	public MovieWatchFutureEntity() {
		super();
	}
	
	public MovieUserEntityPK getPk() {
		return pk;
	}

	public void setPk(MovieUserEntityPK pk) {
		this.pk = pk;
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
