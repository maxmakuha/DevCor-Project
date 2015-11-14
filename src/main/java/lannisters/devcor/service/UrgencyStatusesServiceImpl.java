package lannisters.devcor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.UrgencyStatusesDAO;
import lannisters.devcor.entity.UrgencyStatus;

@Service
public class UrgencyStatusesServiceImpl implements UrgencyStatusesService{

	@Autowired
	private UrgencyStatusesDAO UrgencyStatusesDao;
	
	public List<UrgencyStatus> getAllUrgencyStatuses() {
		return UrgencyStatusesDao.getAllUrgencyStatuses();
	}

}
