package lannisters.devcor.service;

import java.util.List;

import lannisters.devcor.entity.Device;

public interface DevicesService {

	public List<Device> getAllDevices();

	public Device getDeviceById(int deviceId);

	public void addDevice(Device device);

	public void updateDevice(Device device);

	public void deleteDevice(int deviceId);

	public List<Device> getAllDevicesOfRoom(int roomId);
	
	public Device getDeviceBySerial(String deviceSerialId);
	
	public boolean checkSerialExistence(Device device);
}