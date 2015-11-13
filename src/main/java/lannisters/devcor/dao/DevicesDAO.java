package lannisters.devcor.dao;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Device;

public interface DevicesDAO {

	public List<Device> getAllDevices();

	public Device getDeviceById(int deviceId);

	public void addDevice(Device device) throws SQLException;

	public void updateDevice(Device device) throws SQLException;

	public void deleteDevice(int deviceId) throws SQLException;
}