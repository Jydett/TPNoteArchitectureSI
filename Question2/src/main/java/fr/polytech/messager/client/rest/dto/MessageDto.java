package fr.polytech.messager.client.rest.dto;

import fr.polytech.messager.client.gui.model.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
@NoArgsConstructor
@Getter(onMethod = @__({@XmlElement}))
@Setter
public class MessageDto implements Serializable {

    private Long id;

    private String postedTime;

    private String content;

    private UserDto author;

    public Message unwarp() {
        return new Message(id, author.getUsername(), content);
    }

    public static List<Message> unwrap(List<MessageDto> messages) {
        return messages.stream().map(MessageDto::unwarp).collect(Collectors.toList());
    }
}
