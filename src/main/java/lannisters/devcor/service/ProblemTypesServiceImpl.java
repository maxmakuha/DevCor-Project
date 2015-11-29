package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.ProblemTypesDAO;
import lannisters.devcor.entity.ProblemType;

@Service
public class ProblemTypesServiceImpl implements ProblemTypesService {

	@Autowired
	private ProblemTypesDAO problemTypesDao;

	@Override
	public List<ProblemType> getAllProblemTypes() {
		return problemTypesDao.getAllProblemTypes();
	}

	@Override
	public ProblemType getProblemTypeById(int problemTypeId) {
		return problemTypesDao.getProblemTypeById(problemTypeId);
	}

	@Override
	public void updateProblemType(ProblemType problemType) {
		try {
			problemTypesDao.updateProblemType(problemType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addProblemType(ProblemType problemType) {
		try {
			problemTypesDao.addProblemType(problemType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProblemType(int problemType) {
		try {
			problemTypesDao.deleteProblemType(problemType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}