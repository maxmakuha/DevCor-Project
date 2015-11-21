package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.DeviceType;

public interface DeviceTypesService {

	public List<DeviceType> getAllDeviceTypes();
	
	public DeviceType getDeviceTypeById(int deviceTypeId);
	public void updateDeviceType(DeviceType deviceType);
	public void addDeviceType(DeviceType deviceType) ;
	public void deleteDeviceType(int deviceType);
}
