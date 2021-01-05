package com.netflix.series.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.sun.istack.NotNull;

public class SerieCategoryEntityPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_serie", referencedColumnName = "id")
	private SerieEntity serie;

	@NotNull
	@Column(name = "id_category")
	private Long category;

	public SerieCategoryEntityPK() {
		super();
	}

	public SerieEntity getSerie() {
		return serie;
	}

	public void setSerie(SerieEntity serie) {
		this.serie = serie;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
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
