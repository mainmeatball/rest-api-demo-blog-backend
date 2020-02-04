package com.example.helloblog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="local_date_time")
    private LocalDateTime localDateTime;

    @Column(name="content")
    private String content;

    @Column(name="likes")
    private int likes;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String text) {
        this.content = text;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void upvote() {
        this.likes++;
    }

    public void downvote() {
        this.likes--;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", localDateTime=" + localDateTime +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                '}';
    }
}
