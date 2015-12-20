package org.h6.domain;

import javax.persistence.*;

@Entity()
@Table(name = "credentials")
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "userId", nullable = false)
    @PrimaryKeyJoinColumn
    private User user;

    @Basic
    @Column
    private String password;

    public UserCredentials() {
    }

    public UserCredentials(long id, User user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        // TODO
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCredentials that = (UserCredentials) o;

        if (id != that.id) return false;
        if (!user.equals(that.user)) return false;
        return password.equals(that.password);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "id=" + id +
                ", user=" + user +
                ", password='" + password + '\'' +
                '}';
    }
}
