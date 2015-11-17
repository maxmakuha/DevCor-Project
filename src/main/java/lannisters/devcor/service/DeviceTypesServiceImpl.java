package lannisters.devcor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lannisters.devcor.dao.DeviceTypesDAO;
import lannisters.devcor.entity.DeviceType;

@Service
public class DeviceTypesServiceImpl implements DeviceTypesService {

	@Autowired
	private DeviceTypesDAO problemTypesDao;

	public List<DeviceType> getAllDeviceTypes() {
		return problemTypesDao.getAllDeviceTypes();
	}
}
