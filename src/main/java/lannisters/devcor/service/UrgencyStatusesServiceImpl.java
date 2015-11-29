package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.UrgencyStatusesDAO;
import lannisters.devcor.entity.UrgencyStatus;

@Service
public class UrgencyStatusesServiceImpl implements UrgencyStatusesService {

	@Autowired
	private UrgencyStatusesDAO urgencyStatusesDao;

	@Override
	public List<UrgencyStatus> getAllUrgencyStatuses() {
		return urgencyStatusesDao.getAllUrgencyStatuses();
	}

	@Override
	public UrgencyStatus getUrgencyStatusById(int urgencyStatus) {
		return urgencyStatusesDao.getUrgencyStatusById(urgencyStatus);
	}

	@Override
	public void updateUrgencyStatus(UrgencyStatus urgencyStatus) {
		try {
			urgencyStatusesDao.updateUrgencyStatus(urgencyStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUrgencyStatus(UrgencyStatus urgencyStatus) {
		try {
			urgencyStatusesDao.addUrgencyStatus(urgencyStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUrgencyStatus(int urgencyStatus) {
		try {
			urgencyStatusesDao.deleteUrgencyStatus(urgencyStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getUrgencyStatusMinutes(int urgencyStatusId) {
		return urgencyStatusesDao.getUrgencyStatusMinutes(urgencyStatusId);
	}

	@Override
	public UrgencyStatus getUrgencyStatusByTitle(String title) {
		return urgencyStatusesDao.getUrgencyStatusByTitle(title);
	}

	@Override
	public boolean checkUrgencyStatusExistence(UrgencyStatus status) {
		boolean existence;
		try {
			urgencyStatusesDao.getUrgencyStatusByTitle(status.getUrgencyStatus());
			existence = true;
		} catch (EmptyResultDataAccessException e) {
			existence = false;
		}
		return existence;
	}
}