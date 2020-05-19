package fr.polytech.messager.beans;

import fr.polytech.messager.doa.Identifiable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String username;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
