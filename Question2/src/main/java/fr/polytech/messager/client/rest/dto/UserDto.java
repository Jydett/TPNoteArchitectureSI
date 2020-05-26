package fr.polytech.messager.client.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@NoArgsConstructor
@Getter(onMethod = @__({@XmlElement}))
@Setter
public class UserDto implements Serializable {

    private Long id;

    private String username;

}