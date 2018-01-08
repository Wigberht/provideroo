package com.dimbo.model;

public class Message extends Entity {

    private String message;
    private boolean wasRead;
    private String updatedAt;
    private String createdAt;
    private long userId;

    public Message() {
    }

    public Message(long id, String message) {
        super(id);
        this.message = message;
    }

    public Message(long id, String message, long userId) {
        super(id);
        this.message = message;
        this.userId = userId;
    }

    public Message(long id, String message, boolean wasRead, String updatedAt,
        String createdAt, long userId) {
        super(id);
        this.message = message;
        this.wasRead = wasRead;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
