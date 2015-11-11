package lannisters.devcor.entity;

public class Device {

	private int deviceId;
	private String deviceSerialId;
	private DeviceType deviceType;
	private Room room;

	public Device() {
	}

	public Device(int deviceId, String deviceSerialId, DeviceType deviceType,
			Room room) {
		this.deviceId = deviceId;
		this.deviceSerialId = deviceSerialId;
		this.deviceType = deviceType;
		this.room = room;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceSerialId() {
		return deviceSerialId;
	}

	public void setDeviceSerialId(String deviceSerialId) {
		this.deviceSerialId = deviceSerialId;
	}

	public int getRoomId() {
		return room.getRoomId();
	}

	public void setRoomId(int roomId) {
		if (this.room == null)
			this.room = new Room();
		this.room.setRoomId(roomId);
	}

	public String getRoomNumber() {
		return room.getRoomNumber();
	}

	public void setRoomNumber(String roomNumber) {
		if (this.room == null)
			this.room = new Room();
		this.room.setRoomNumber(roomNumber);
	}

	public int getDeviceTypeId() {
		return deviceType.getDeviceTypeId();
	}

	public void setDeviceTypeId(int deviceTypeId) {
		if (this.deviceType == null)
			this.deviceType = new DeviceType();
		this.deviceType.setDeviceTypeId(deviceTypeId);
	}

	public String getDeviceType() {
		return deviceType.getDeviceType();
	}

	public void setDeviceType(String deviceType) {
		if (this.deviceType == null)
			this.deviceType = new DeviceType();
		this.deviceType.setDeviceType(deviceType);
	}

	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", deviceSerialId="
				+ deviceSerialId + ", deviceType=" + deviceType + ", room="
				+ room + "]";
	}
}