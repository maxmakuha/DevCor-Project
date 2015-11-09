package lannisters.devcor.service;

import java.util.List;
import lannisters.devcor.dao.DevicesDAO;
import lannisters.devcor.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

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
		devicesDao.addDevice(device);
	}

	public void deleteDevice(int deviceId) {
		devicesDao.deleteDevice(deviceId);
	}
}