package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.dao.DevicesDAO;
import lannisters.devcor.entity.Device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevicesServiceImpl implements DevicesService {

	@Autowired
	private DevicesDAO devicesDao;

	public List<Device> getAllDevices() {
		return devicesDao.getAllDevices();
	}

	public Device getDeviceById(int deviceId) {
		return devicesDao.getDeviceById(deviceId);
	}

	public void addDevice(Device device) throws SQLException {
		devicesDao.addDevice(device);
	}

	public void updateDevice(Device device) throws SQLException {
		devicesDao.updateDevice(device);
	}

	public void deleteDevice(int deviceId) throws SQLException {
		devicesDao.deleteDevice(deviceId);
	}

	public List<Device> getAllDevicesOfRoom(int roomId) throws SQLException {
		return devicesDao.getAllDevicesOfRoom(roomId);
	}
}