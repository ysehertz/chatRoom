package com.isljq.qqcommon;

import java.util.Objects;

/**
 * ClassName: User
 * Package: com.isljq.qqcommon
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/12
 */
public class User implements java.io.Serializable {
    private static final long serialVersionUID = 132423432423L;
    private String userId;
    private String password;

    public User() {}
    public User(String userId, String password) {

        this.userId = userId;
        this.password = password;
    }

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, password);
    }
}
