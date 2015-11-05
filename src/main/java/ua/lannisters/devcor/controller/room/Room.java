package com.natalia;

public class Room {
	
	private int roomId;
	private String roomNumber;
	private String technician;
	private String device;
	
	public Room() {}
	
	
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String aRoomNumber) {
		this.roomNumber = aRoomNumber;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String aTechnician) {
		this.technician = aTechnician;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String aDevice) {
		this.device = aDevice;
	}

	@Override
	public String toString() {
		return "Room [roomNumber = " + roomNumber + ", technician = " + technician +
				", device = " + device + "]";
	}
	
	
}
