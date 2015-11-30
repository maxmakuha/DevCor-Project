package lannisters.devcor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Device;
import lannisters.devcor.orm.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DevicesDAOImpl implements DevicesDAO {

	private static final String SQL_SELECT_ALL_DEVICES = "SELECT device.device_id, device.device_serial_id, device_type.device_type, room.room_number FROM ((device INNER JOIN device_type ON device.device_type_id = device_type.device_type_id) INNER JOIN room ON device.room_id = room.room_id)";
	private static final String SQL_SELECT_DEVICE_BY_ID = "SELECT device.device_id, device.device_serial_id, device_type.device_type, room.room_number FROM ((device INNER JOIN device_type ON device.device_type_id = device_type.device_type_id) INNER JOIN room ON device.room_id = room.room_id) WHERE device_id=?";
	private static final String SQL_INSERT_DEVICE = "INSERT INTO device(device_serial_id, device_type_id, room_id) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE_DEVICE = "UPDATE device SET device_serial_id = ?, device_type_id = ?, room_id = ? WHERE device_id = ?";
	private static final String SQL_DELETE_DEVICE = "DELETE device WHERE device_id=?";
	private static final String SQL_SELECT_ALL_DEVICES_OF_ROOM = SQL_SELECT_ALL_DEVICES + " WHERE device.room_id = ?";
	private static final String SQL_GET_DEVICE_BY_SERIAL = SQL_SELECT_ALL_DEVICES + " WHERE device_serial_id = ?";
	private static final String SQL_SELECT_DEVICES_BY_TYPE = "SELECT device.device_id, device.device_serial_id, device_type.device_type, room.room_number FROM ((device INNER JOIN device_type ON device.device_type_id = device_type.device_type_id) INNER JOIN room ON device.room_id = room.room_id) WHERE device.device_type_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Device> getAllDevices() {
		return jdbcTemplate.query(SQL_SELECT_ALL_DEVICES, new DeviceMapper());
	}

	@Override
	public Device getDeviceById(int deviceId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_DEVICE_BY_ID, new DeviceMapper(), deviceId);
	}

	@Override
	public void addDevice(Device device) throws SQLException {
		PreparedStatement ps = null;
		ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_DEVICE);
		ps.setString(1, device.getDeviceSerialId());
		ps.setInt(2, device.getDeviceTypeId());
		ps.setInt(3, device.getRoomId());
		ps.executeUpdate();
		if (ps != null)
				ps.close();
	}

	@Override
	public void updateDevice(Device device) throws SQLException {
		PreparedStatement ps = null;
		ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_DEVICE);
		ps.setString(1, device.getDeviceSerialId());
		ps.setInt(2, device.getDeviceTypeId());
		ps.setInt(3, device.getRoomId());
		ps.setInt(4, device.getDeviceId());
		ps.executeUpdate();
		if (ps != null)
				ps.close();
	}

	@Override
	public void deleteDevice(int deviceId) throws SQLException {
		PreparedStatement ps = null;
		ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_DEVICE);
		ps.setInt(1, deviceId);
		ps.executeUpdate();
		if (ps != null)
				ps.close();
	}

	@Override
	public List<Device> getAllDevicesOfRoom(int roomId) {
		return jdbcTemplate.query(SQL_SELECT_ALL_DEVICES_OF_ROOM, new Integer[] { roomId }, new DeviceMapper());
	}

	@Override
	public Device getDeviceBySerial(String deviceSerialId) {
		return jdbcTemplate.queryForObject(SQL_GET_DEVICE_BY_SERIAL, new DeviceMapper(), deviceSerialId);
	}

	@Override
	public List<Device> getDevicesByType(int deviceTypeId) {
		return jdbcTemplate.query(SQL_SELECT_DEVICES_BY_TYPE, new DeviceMapper(), deviceTypeId);
	}
}