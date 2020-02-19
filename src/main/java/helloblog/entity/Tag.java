package helloblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name="tags")
public class Tag {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "tag_id")
    private int id;

    @Column (name = "tag_name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy="tags")
    private Set<Message> messages;

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
