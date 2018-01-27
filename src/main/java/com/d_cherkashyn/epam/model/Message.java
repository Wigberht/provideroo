package com.d_cherkashyn.epam.model;

public class Message extends Entity {
    
    private String message;
    private boolean wasRead;
    private String updatedAt;
    private String createdAt;
    private long userId;
    private long chatId;
    
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
                   String createdAt, long userId, long chatId) {
        super(id);
        this.message = message;
        this.wasRead = wasRead;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.userId = userId;
        this.chatId = chatId;
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
    
    public boolean wasRead() {
        return wasRead;
    }
    
    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }
    
    public String getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public long getChatId() {
        return chatId;
    }
    
    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("message='").append(message).append('\'');
        sb.append(", wasRead=").append(wasRead);
        sb.append(", updatedAt='").append(updatedAt).append('\'');
        sb.append(", createdAt='").append(createdAt).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", chatId=").append(chatId);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
