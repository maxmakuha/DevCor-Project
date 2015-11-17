package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import lannisters.devcor.entity.DeviceType;

import org.springframework.jdbc.core.RowMapper;

public class DeviceTypeMapper implements RowMapper<DeviceType> {

	public DeviceType mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new DeviceType(rs.getInt("device_type_id"), rs.getString("device_type"));
	}
}
