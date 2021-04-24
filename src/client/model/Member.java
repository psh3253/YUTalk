package client.model;

import java.io.Serializable;

public class Member implements Serializable, Comparable<Member>{

    private String userId;
    private String name;
    private String status_message;
    private Boolean isBlocked;

    public Member(String userId, String name, String status_message, Boolean isBlocked) {
        this.userId = userId;
        this.name = name;
        this.status_message = status_message;
        this.isBlocked = isBlocked;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public int compareTo(Member member) {
        return this.getName().compareTo(member.getName());
    }
}
