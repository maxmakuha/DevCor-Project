package ua.lannisters.devcor.controller.player;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PlayerRowMapper implements RowMapper<Player>{

	public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		 return  new Player(rs.getInt("PLAYER_ID"),
                 rs.getString("PLAYER_EMAIL"),
                 rs.getString("FIRS_NAME"),
                 rs.getString("LAST_NAME"),
                 rs.getString("PASSWORD"),
                 rs.getInt("PHONE_NUMBER"),
                 rs.getInt("ROLE_ID")
                 );
		
	}

}