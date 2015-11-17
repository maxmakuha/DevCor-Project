package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Device;

public interface DevicesService {

	public List<Device> getAllDevices();

	public Device getDeviceById(int deviceId);

	public void addDevice(Device device);
	
	public void updateDevice(Device device);

	public void deleteDevice(int deviceId);
	
	public List<Device> getAllDevicesOfRoom(int roomId) throws SQLException;
}