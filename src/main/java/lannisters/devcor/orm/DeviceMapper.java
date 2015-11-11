package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Device;
import org.springframework.jdbc.core.RowMapper;

public class DeviceMapper implements RowMapper<Device> {

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