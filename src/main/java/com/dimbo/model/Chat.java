package com.dimbo.model;

import java.util.List;

public class Chat extends Entity {

    private String title;
    private List<Message> messages;

    public Chat(long id, String title) {
        super(id);
        this.title = title;
    }

    public Chat(long id, String title, List<Message> messages) {
        this(id, title);
        this.messages = messages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Chat chat = (Chat) o;

        if (id != chat.id) {
            return false;
        }
        if (!title.equals(chat.title)) {
            return false;
        }
        return messages.equals(chat.messages);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + messages.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Chat{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", messages=").append(messages);
        sb.append('}');
        return sb.toString();
    }
}
