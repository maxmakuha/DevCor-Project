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

	public void addDevice(Device device) {
		try {
			devicesDao.addDevice(device);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDevice(Device device) {
		try {
			devicesDao.updateDevice(device);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteDevice(int deviceId) {
		try {
			devicesDao.deleteDevice(deviceId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Device> getAllDevicesOfRoom(int roomId) {
		return devicesDao.getAllDevicesOfRoom(roomId);
	}
}