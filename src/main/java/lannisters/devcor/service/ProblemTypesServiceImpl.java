package lannisters.devcor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.ProblemTypesDAO;
import lannisters.devcor.entity.ProblemType;

@Service
public class ProblemTypesServiceImpl implements ProblemTypesService{

	@Autowired
	private ProblemTypesDAO problemTypesDao;
	
	public List<ProblemType> getAllProblemTypes() {
		return problemTypesDao.getAllProblemTypes();
	}

}
