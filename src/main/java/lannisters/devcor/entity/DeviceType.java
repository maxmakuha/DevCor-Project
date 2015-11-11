package lannisters.devcor.entity;

public class DeviceType {

	private int deviceTypeId;
	private String deviceType;
	
	public DeviceType(){
	}

	public DeviceType(int deviceTypeId, String deviceType) {
		this.deviceTypeId = deviceTypeId;
		this.deviceType = deviceType;
	}

	public int getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return "DeviceType [deviceTypeId=" + deviceTypeId + ", deviceType="
				+ deviceType + "]";
	}
}