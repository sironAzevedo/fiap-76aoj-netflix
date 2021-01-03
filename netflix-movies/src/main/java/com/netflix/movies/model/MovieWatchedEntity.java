package com.netflix.movies.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "tb_movie_watched")
public class MovieWatchedEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MovieWatchedEntityPK pk;

	@NotNull
	@Column(name = "date_watched")
	private Date date;

	public MovieWatchedEntity() {
		super();
	}

	public MovieWatchedEntityPK getPk() {
		return pk;
	}

	public void setPk(MovieWatchedEntityPK pk) {
		this.pk = pk;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
