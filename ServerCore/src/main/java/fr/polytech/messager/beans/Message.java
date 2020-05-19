package fr.polytech.messager.beans;

import fr.polytech.messager.doa.Identifiable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Message implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private LocalDateTime postedTime;

    private String content;

    @ManyToOne
    private User author;

    public Message(LocalDateTime postedTime, String content, User author) {
        this.postedTime = postedTime;
        this.content = content;
        this.author = author;
    }
}
