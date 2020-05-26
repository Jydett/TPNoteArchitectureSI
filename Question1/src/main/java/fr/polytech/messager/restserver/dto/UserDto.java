package fr.polytech.messager.restserver.dto;

import fr.polytech.messager.beans.User;
import fr.polytech.messager.doa.user.UserDao;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@NoArgsConstructor
@Getter(onMethod = @__({@XmlElement}))
public class UserDto implements Serializable {

    private Long id;

    private String username;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
