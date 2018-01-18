package com.dimbo.model;

public class User extends Entity {
    
    private String login;
    private String password;
    private boolean banned;
    private long roleId;
    
    public User() {
    }
    
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    public User(String login, String password, boolean banned, long roleId) {
        this.login = login;
        this.password = password;
        this.banned = banned;
        this.roleId = roleId;
    }
    
    public User(long id, String login, String password, boolean banned, long roleId) {
        super(id);
        this.login = login;
        this.password = password;
        this.banned = banned;
        this.roleId = roleId;
    }
    
    public boolean isAdmin() {
        return roleId == Roles.ADMIN.getId();
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isBanned() {
        return banned;
    }
    
    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    
    public long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        User user = (User) o;
        
        return login.equals(user.login);
    }
    
    @Override
    public int hashCode() {
        return login.hashCode();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=")
          .append(id);
        sb.append(", login='")
          .append(login)
          .append('\'');
        sb.append(", password='")
          .append(password)
          .append('\'');
        sb.append(", banned=")
          .append(banned);
        sb.append(", roleId=")
          .append(roleId);
        sb.append('}');
        return sb.toString();
    }
}
