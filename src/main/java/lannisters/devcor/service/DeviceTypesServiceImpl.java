package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.DeviceTypesDAO;
import lannisters.devcor.entity.DeviceType;

@Service
public class DeviceTypesServiceImpl implements DeviceTypesService {

	@Autowired
	private DeviceTypesDAO deviceTypesDao;

	@Override
	public List<DeviceType> getAllDeviceTypes() {
		return deviceTypesDao.getAllDeviceTypes();
	}

	@Override
	public DeviceType getDeviceTypeById(int deviceTypeId) {
		return deviceTypesDao.getDeviceTypeById(deviceTypeId);
	}

	@Override
	public void updateDeviceType(DeviceType deviceType) {
		try {
			deviceTypesDao.updateDeviceType(deviceType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addDeviceType(DeviceType deviceType) {
		try {
			deviceTypesDao.addDeviceType(deviceType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDeviceType(int deviceType) {
		try {
			deviceTypesDao.deleteDeviceType(deviceType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public DeviceType getDeviceTypeByTitle(String title) {
		return deviceTypesDao.getDeviceTypeByTitle(title);
	}

	@Override
	public boolean checkDeviceTypeExistence(DeviceType type) {
		boolean existence;
		try {
			deviceTypesDao.getDeviceTypeByTitle(type.getDeviceType());
			existence = true;
		} catch (EmptyResultDataAccessException e) {
			existence = false;
		}
		return existence;
	}
}