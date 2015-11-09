package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Device;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public class DeviceMapper implements RowMapper<Device> {

	/**
	 * Creates Device and fills fields with database values recieved from
	 * ResultSet.
	 * 
	 * @param rs
	 *            ResultSet - table which has all data recieved by query.
	 * @param rowNum
	 *            number of ResultSet rows.
	 * @return Device with fields: deviceId, deviceSerialId, deviceType,
	 *         roomNumber.
	 */
	public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
		Device device = new Device();
		device.setDeviceId(rs.getInt("device_id"));
		device.setDeviceSerialId(rs.getString("device_serial_id"));
		// device.setDeviceTypeId(rs.getInt("device_type_id"));
		device.setDeviceType(rs.getString("device_type"));
		// device.setRoomId(rs.getInt("room_id"));
		device.setRoomNumber(rs.getString("room_number"));
		return device;
	}
}