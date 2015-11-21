package lannisters.devcor.service;

import java.util.List;
import lannisters.devcor.entity.UrgencyStatus;

public interface UrgencyStatusesService {

	public List<UrgencyStatus> getAllUrgencyStatuses();
	
	public UrgencyStatus getUrgencyStatusById(int urgencyStatus);
	public void updateUrgencyStatus(UrgencyStatus urgencyStatus);
	public void addUrgencyStatus(UrgencyStatus urgencyStatus) ;
	public void deleteUrgencyStatus(int urgencyStatus);
}
