package com.example.helloblog.entity;

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

    public Message() {
    }

    public Message(LocalDateTime localDateTime, String content) {
        this.localDateTime = localDateTime;
        this.content = content;
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
