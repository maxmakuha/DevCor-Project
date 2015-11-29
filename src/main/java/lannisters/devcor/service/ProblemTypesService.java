package lannisters.devcor.service;

import java.util.List;

import lannisters.devcor.entity.ProblemType;

public interface ProblemTypesService {

	public List<ProblemType> getAllProblemTypes();
	public ProblemType getProblemTypeById(int problemTypeId);
	public void updateProblemType(ProblemType problemType);
	public void addProblemType(ProblemType problemType);
	public void deleteProblemType(int problemType);
}
