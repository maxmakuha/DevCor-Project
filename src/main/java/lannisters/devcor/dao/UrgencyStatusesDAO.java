package lannisters.devcor.dao;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.UrgencyStatus;

public interface UrgencyStatusesDAO {
	
	List<UrgencyStatus> getAllUrgencyStatuses();

	public UrgencyStatus getUrgencyStatusById(int urgencyStatus);
	public void updateUrgencyStatus(UrgencyStatus urgencyStatus) throws SQLException;
	public void addUrgencyStatus(UrgencyStatus urgencyStatus) throws SQLException;
	public void deleteUrgencyStatus(int urgencyStatus) throws SQLException;

	int getUrgencyStatusMinutes(int urgencyStatusId);

}
