package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.UrgencyStatusesDAO;
import lannisters.devcor.entity.UrgencyStatus;

@Service
public class UrgencyStatusesServiceImpl implements UrgencyStatusesService {

	@Autowired
	private UrgencyStatusesDAO UrgencyStatusesDao;

	@Override
	public List<UrgencyStatus> getAllUrgencyStatuses() {
		return UrgencyStatusesDao.getAllUrgencyStatuses();
	}

	@Override
	public UrgencyStatus getUrgencyStatusById(int urgencyStatus) {
		return UrgencyStatusesDao.getUrgencyStatusById(urgencyStatus);
	}

	@Override
	public void updateUrgencyStatus(UrgencyStatus urgencyStatus) {
		try {
			UrgencyStatusesDao.updateUrgencyStatus(urgencyStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUrgencyStatus(UrgencyStatus urgencyStatus) {
		try {
			UrgencyStatusesDao.addUrgencyStatus(urgencyStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUrgencyStatus(int urgencyStatus) {
		try {
			UrgencyStatusesDao.deleteUrgencyStatus(urgencyStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getUrgencyStatusMinutes(int urgencyStatusId) {
		return UrgencyStatusesDao.getUrgencyStatusMinutes(urgencyStatusId);
	}

}
