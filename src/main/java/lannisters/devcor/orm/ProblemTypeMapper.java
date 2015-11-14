package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import lannisters.devcor.entity.ProblemType;

import org.springframework.jdbc.core.RowMapper;


public class ProblemTypeMapper implements RowMapper<ProblemType> {

	public ProblemType mapRow(ResultSet resSet, int arg1) throws SQLException {
		return new ProblemType(resSet.getInt("problem_type_id"), resSet.getString("problem_type"));
	}

}
