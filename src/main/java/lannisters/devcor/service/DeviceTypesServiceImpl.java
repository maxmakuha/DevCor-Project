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
	public void updateDeviceType(DeviceType deviceType) throws SQLException {
		deviceTypesDao.updateDeviceType(deviceType);
	}

	@Override
	public void addDeviceType(DeviceType deviceType) throws SQLException {
		deviceTypesDao.addDeviceType(deviceType);
	}

	@Override
	public void deleteDeviceType(int deviceType) throws SQLException {
		deviceTypesDao.deleteDeviceType(deviceType);
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