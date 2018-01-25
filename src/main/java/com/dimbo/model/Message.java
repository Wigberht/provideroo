package com.dimbo.model;

public class Message extends Entity {
    
    private String message;
    private boolean read;
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
    
    public Message(long id, String message, boolean read, String updatedAt,
                   String createdAt, long userId) {
        super(id);
        this.message = message;
        this.read = read;
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
    
    public boolean isRead() {
        return read;
    }
    
    public void setRead(boolean read) {
        this.read = read;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("message='").append(message).append('\'');
        sb.append(", read=").append(read);
        sb.append(", updatedAt='").append(updatedAt).append('\'');
        sb.append(", createdAt='").append(createdAt).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
