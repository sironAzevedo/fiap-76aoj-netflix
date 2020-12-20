package com.netflix.movies.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_movie")
public class Movie implements Serializable {
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

	@ManyToMany
	@JoinTable(
			name = "tb_movie_category", 
			joinColumns = @JoinColumn(name = "id_movie"), 
			inverseJoinColumns = @JoinColumn(name = "id_category"))
	private List<Category> categories;

}
