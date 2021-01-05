package com.netflix.series.repository.custom.impl;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.netflix.series.handler.exception.InternalErrorException;
import com.netflix.series.model.dto.TopSerieCategoryDTO;
import com.netflix.series.repository.custom.ISerieCategoryCustomRepository;

@Repository
public class SerieCategoryCustomRepositoryImpl implements ISerieCategoryCustomRepository {
	
	@Autowired
    private JdbcTemplate template;

	@Override
	public List<TopSerieCategoryDTO> getTopSerieByCategory() {
		StringBuilder query = new StringBuilder();
		query
			.append("SELECT ")
			.append("m.ID as cod_serie, ")
			.append("m.TiTLE as title_serie, ")
			.append("mc.ID_CATEGORY as category, ")
			.append("count(m.ID) as amount ")
			.append("FROM TB_SERIE_WATCHED mw ")
			.append("INNER JOIN TB_SERIE m ")
			.append("ON m.ID = mw.ID_SERIE ")
			.append("INNER JOIN TB_SERIE_CATEGORY mc ")
			.append("ON mc.ID_SERIE = mw.ID_SERIE ")
			.append("GROUP BY  m.ID, mc.ID_CATEGORY ")
			.append("ORDER BY amount DESC ");
		
		return template.query(query.toString(), (rs, rowNum) -> dto(rs));
	}

	private TopSerieCategoryDTO dto(ResultSet rs) {
		try {
			
			TopSerieCategoryDTO dto = new TopSerieCategoryDTO();
			dto.setCategory(rs.getLong("category"));
			dto.setCodSerie(rs.getLong("cod_serie"));
			dto.setAmount(rs.getLong("amount"));
			return dto;
		} catch (Exception e) {
			 throw new InternalErrorException(e);
		}
	}
}
