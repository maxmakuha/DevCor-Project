package lannisters.devcor.dao;

import java.util.List;
import lannisters.devcor.entity.Device;

public interface DevicesDAO {

	public List<Device> getAllDevices();

	public Device getDeviceById(int deviceId);

	public void addDevice(Device device);

	public void deleteDevice(int deviceId);
}