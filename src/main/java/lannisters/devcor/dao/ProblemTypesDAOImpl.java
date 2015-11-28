package lannisters.devcor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lannisters.devcor.entity.ProblemType;
import lannisters.devcor.orm.ProblemTypeMapper;
import lannisters.devcor.orm.RoomMapper;

@Repository
public class ProblemTypesDAOImpl implements ProblemTypesDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SQL_SELECT_ALL_PROBLEM_TYPES = "SELECT * FROM problem_type";
	private static final String SQL_SELECT_PROBLEM_TYPE_BY_ID = "SELECT problem_type_id, problem_type FROM problem_type WHERE problem_type.problem_type_id=?";
	private static final String SQL_UPDATE_PROBLEM_TYPE = "UPDATE problem_type SET problem_type = ? WHERE problem_type_id = ?";
	private static final String SQL_INSERT_PROBLEM_TYPE = "INSERT INTO problem_type( problem_type) VALUES ( ?)";
	private static final String SQL_DELETE_PROBLEM_TYPE = "DELETE problem_type WHERE problem_type_id=?";

	public List<ProblemType> getAllProblemTypes() {
		return jdbcTemplate.query(SQL_SELECT_ALL_PROBLEM_TYPES, new ProblemTypeMapper());
	}

	@Override
	public ProblemType getProblemTypeById(int problemTypeId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_PROBLEM_TYPE_BY_ID, new ProblemTypeMapper(), problemTypeId);
	}

	@Override
	public void updateProblemType(ProblemType problemType) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_PROBLEM_TYPE);
			
			ps.setString(1, problemType.getProblemType());
			ps.setInt(2, problemType.getProblemTypeId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void addProblemType(ProblemType problemType) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_PROBLEM_TYPE);
			
			ps.setString(1, problemType.getProblemType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void deleteProblemType(int problemType) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_PROBLEM_TYPE);
			ps.setInt(1, problemType);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

}
