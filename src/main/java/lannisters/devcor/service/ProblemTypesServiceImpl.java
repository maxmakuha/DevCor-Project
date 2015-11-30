package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	public void updateProblemType(ProblemType problemType) throws SQLException {
		problemTypesDao.updateProblemType(problemType);
	}

	@Override
	public void addProblemType(ProblemType problemType) throws SQLException {
		problemTypesDao.addProblemType(problemType);
	}

	@Override
	public void deleteProblemType(int problemType) throws SQLException {
		problemTypesDao.deleteProblemType(problemType);

	}

	@Override
	public ProblemType getProblemTypeByTitle(String title) {
		return problemTypesDao.getProblemTypeByTitle(title);
	}

	@Override
	public boolean checkProblemTypeExistence(ProblemType type) {
		boolean existence;
		try {
			problemTypesDao.getProblemTypeByTitle(type.getProblemType());
			existence = true;
		} catch (EmptyResultDataAccessException e) {
			existence = false;
		}
		return existence;
	}
}