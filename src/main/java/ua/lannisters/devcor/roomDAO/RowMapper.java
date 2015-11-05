package com.natalia;

import java.sql.ResultSet;

public interface RowMapper {
	
	public Room mapRow(ResultSet rs, String room);

}
