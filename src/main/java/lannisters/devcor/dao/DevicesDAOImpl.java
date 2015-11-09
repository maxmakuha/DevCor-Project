package lannisters.devcor.dao;

import java.util.List;

import lannisters.devcor.entity.Device;
import lannisters.devcor.orm.DeviceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

@Repository
public class DevicesDAOImpl implements DevicesDAO {

	private static final String SQL_SELECT_ALL_DEVICES = "SELECT device.device_id, device.device_serial_id, device_type.device_type, room.room_number FROM ((device INNER JOIN device_type ON device.device_type_id = device_type.device_type_id) INNER JOIN room ON device.room_id = room.room_id)";
	private static final String SQL_SELECT_DEVICE_BY_ID = "SELECT device.device_id, device.device_serial_id, device_type.device_type, room.room_number FROM ((device INNER JOIN device_type ON device.device_type_id = device_type.device_type_id) INNER JOIN room ON device.room_id = room.room_id) WHERE device_id=?";
	private static final String SQL_INSERT_DEVICE = "INSERT INTO device(device_serial_id, device_type_id, room_id) VALUES (?, ?, ?)";
	private static final String SQL_DELETE_DEVICE = "DELETE device WHERE device_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Device> getAllDevices() {
		return jdbcTemplate.query(SQL_SELECT_ALL_DEVICES, new DeviceMapper());
	}

	public Device getDeviceById(int deviceId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_DEVICE_BY_ID,
				new DeviceMapper(), deviceId);
	}

	public void addDevice(Device device) {
		jdbcTemplate.update(SQL_INSERT_DEVICE, device);
	}

	public void deleteDevice(int deviceId) {
		jdbcTemplate.update(SQL_DELETE_DEVICE, deviceId);
	}
}