package org.h6.dto;

public class UserDTO {

    private String username;
    private String firstName;
    private String secondName;
    private long id = -1;

    public UserDTO() {
    }

    public UserDTO(String username, String firstName, String secondName) {
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
