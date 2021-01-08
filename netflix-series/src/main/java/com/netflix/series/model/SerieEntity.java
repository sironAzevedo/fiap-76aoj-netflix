package com.netflix.series.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_serie")
public class SerieEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "TITLE")
	private String title;

	@NotNull
	@Column(name = "SUMMARY")
	private String summary;

	@NotNull
	@Column(name = "RELEASE_DATE")
	private Date releaseDate;

	@NotNull
	@Column(name = "SEASON")
	private Long season;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "serie", fetch = FetchType.EAGER)
	private List<SerieKeyWordEntity> keywords;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "serie", fetch = FetchType.EAGER)
	private List<SerieCategoryEntity> categories;

	public SerieEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getSeason() {
		return season;
	}

	public void setSeason(Long season) {
		this.season = season;
	}

	public List<SerieKeyWordEntity> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<SerieKeyWordEntity> keywords) {
		this.keywords = keywords;
	}

	public List<SerieCategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(List<SerieCategoryEntity> categories) {
		this.categories = categories;
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
