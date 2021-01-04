package com.netflix.movies.repository.custom.impl;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.netflix.movies.handler.exception.InternalErrorException;
import com.netflix.movies.model.dto.TopMovieCategoryDTO;
import com.netflix.movies.repository.custom.IMovieCategoryCustomRepository;

@Repository
public class MovieCategoryCustomRepositoryImpl implements IMovieCategoryCustomRepository {
	
	@Autowired
    private JdbcTemplate template;

	@Override
	public List<TopMovieCategoryDTO> getTopMovieByCategory() {
		StringBuilder query = new StringBuilder();
		query
			.append("SELECT ")
			.append("m.ID as cod_movie, ")
			.append("m.TiTLE as title_movie, ")
			.append("mc.ID_CaTEGORY as category, ")
			.append("count(m.ID) as amount ")
			.append("FROM TB_MOVIE_WATCHED mw ")
			.append("INNER JOIN TB_MOVIE m ")
			.append("ON m.ID = mw.ID_MOVIE ")
			.append("INNER JOIN TB_MOVIE_CATEGORY mc ")
			.append("ON mc.ID_MOVIE = mw.ID_MOVIE ")
			.append("GROUP BY  m.ID, mc.ID_CaTEGORY ")
			.append("ORDER BY amount DESC ");
		
		return template.query(query.toString(), (rs, rowNum) -> dto(rs));
	}

	private TopMovieCategoryDTO dto(ResultSet rs) {
		try {
			
			TopMovieCategoryDTO dto = new TopMovieCategoryDTO();
			dto.setCategory(rs.getLong("category"));
			dto.setCodMovie(rs.getLong("cod_movie"));
			dto.setAmount(rs.getLong("amount"));
			return dto;
		} catch (Exception e) {
			 throw new InternalErrorException(e);
		}
	}
}
