package com.natalia;

public class Room {
	
	private int roomNumber;
	private String technician;
	private String device;
	
	public Room(int aRoomNumber, String aTechnicial, String aDevice) {
		this.roomNumber = aRoomNumber;
		this.technician = aTechnicial;
		this.device = aDevice;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int aRoomNumber) {
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
}
