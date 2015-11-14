package lannisters.devcor.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lannisters.devcor.entity.ProblemType;
import lannisters.devcor.orm.ProblemTypeMapper;

@Repository
public class ProblemTypesDAOImpl implements ProblemTypesDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SQL_SELECT_ALL_PROBLEM_TYPES = "SELECT * FROM problem_type";
	
	public List<ProblemType> getAllProblemTypes() {
		return jdbcTemplate.query(SQL_SELECT_ALL_PROBLEM_TYPES, new ProblemTypeMapper());
	}

}
