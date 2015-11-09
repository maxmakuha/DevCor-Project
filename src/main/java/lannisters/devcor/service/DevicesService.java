package lannisters.devcor.service;

import java.util.List;
import lannisters.devcor.entity.Device;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public interface DevicesService {

	public List<Device> getAllDevices();
	
	public Device getDeviceById(int deviceId);
	
	public void addDevice(Device device);

	public void deleteDevice(int deviceId);
}