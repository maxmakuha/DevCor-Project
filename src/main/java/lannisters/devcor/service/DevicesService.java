package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Device;

public interface DevicesService {

	public List<Device> getAllDevices();

	public Device getDeviceById(int deviceId);

	public void addDevice(Device device) throws SQLException;
	
	public void updateDevice(Device device) throws SQLException;

	public void deleteDevice(int deviceId) throws SQLException;
	
	public List<Device> getAllDevicesOfRoom(int roomId) throws SQLException;
}