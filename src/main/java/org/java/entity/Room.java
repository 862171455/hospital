package org.java.entity;

public class Room {
    private Integer roomId;

    private Integer roomLc;

    private String roomName;

    private String roomCon;

    private Integer roomStaId;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomLc() {
        return roomLc;
    }

    public void setRoomLc(Integer roomLc) {
        this.roomLc = roomLc;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getRoomCon() {
        return roomCon;
    }

    public void setRoomCon(String roomCon) {
        this.roomCon = roomCon == null ? null : roomCon.trim();
    }

    public Integer getRoomStaId() {
        return roomStaId;
    }

    public void setRoomStaId(Integer roomStaId) {
        this.roomStaId = roomStaId;
    }
}