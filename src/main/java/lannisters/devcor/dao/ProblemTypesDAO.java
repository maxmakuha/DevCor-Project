package lannisters.devcor.dao;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Device;
import lannisters.devcor.entity.ProblemType;
import lannisters.devcor.entity.Room;

public interface ProblemTypesDAO {
	
	List<ProblemType> getAllProblemTypes();
	public ProblemType getProblemTypeById(int problemTypeId);
	public void updateProblemType(ProblemType problemType) throws SQLException;
	public void addProblemType(ProblemType problemType) throws SQLException;
	public void deleteProblemType(int problemType) throws SQLException;

}
