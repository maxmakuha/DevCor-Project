package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;
import lannisters.devcor.entity.UrgencyStatus;

public interface UrgencyStatusesService {

	public List<UrgencyStatus> getAllUrgencyStatuses();

	public UrgencyStatus getUrgencyStatusById(int urgencyStatus);

	public void updateUrgencyStatus(UrgencyStatus urgencyStatus) throws SQLException;

	public void addUrgencyStatus(UrgencyStatus urgencyStatus) throws SQLException;

	public void deleteUrgencyStatus(int urgencyStatus) throws SQLException;

	public int getUrgencyStatusMinutes(int urgencyStatusId);

	public UrgencyStatus getUrgencyStatusByTitle(String title);
	
	public boolean checkUrgencyStatusExistence(UrgencyStatus status);
}
