package com.DTO;

import javax.persistence.*;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "uri")
    private String uri;

    @ManyToOne
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
