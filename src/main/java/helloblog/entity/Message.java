package helloblog.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="local_date_time")
    @NotNull
    private LocalDateTime localDateTime;

    @Column(name="content")
    @NotNull
    private String content;

    @Column(name="likes")
    private int likes;

    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull
    private User user;

    @ManyToMany
    @JoinTable(
            name = "messages_tags",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
