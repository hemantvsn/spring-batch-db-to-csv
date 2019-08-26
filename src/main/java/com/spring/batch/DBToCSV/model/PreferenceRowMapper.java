package com.spring.batch.DBToCSV.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PreferenceRowMapper implements RowMapper<Preference> {

	@Override
	public Preference mapRow(ResultSet rs, int rowNum) throws SQLException {
		Preference p = new Preference();
		p.setId(rs.getInt("id"));
		p.setPreferenceKey(rs.getString("preference_key"));
		p.setPreferenceType(rs.getString("preference_type"));
		p.setPreferenceValue(rs.getString("preference_value"));
		
		return p;
	}

}
