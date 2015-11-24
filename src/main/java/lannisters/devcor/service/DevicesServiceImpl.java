package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.dao.DevicesDAO;
import lannisters.devcor.entity.Device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	public Device getDeviceBySerial(String deviceSerialId) {
		return devicesDao.getDeviceBySerial(deviceSerialId);
	}

	public boolean checkSerialExistence(Device device) {
		boolean existence;
		try {
			devicesDao.getDeviceBySerial(device.getDeviceSerialId());
			existence = true;
		} catch (EmptyResultDataAccessException e) {
			existence = false;
		}
		return existence;
	}
}