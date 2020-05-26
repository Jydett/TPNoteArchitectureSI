package fr.polytech.messager.restserver.dto;

import fr.polytech.messager.beans.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
@NoArgsConstructor
@Getter(onMethod = @__({@XmlElement}))
public class MessageDto implements Serializable {

    private Long id;

    private String postedTime;

    private String content;

    private UserDto author;

    public MessageDto(Message message) {
        this.id = message.getId();
        this.postedTime = DateTimeFormatter.ISO_INSTANT.format(
                message.getPostedTime().toInstant(ZoneOffset.UTC));
        this.content = message.getContent();
        this.author = new UserDto(message.getAuthor());
    }

    public static List<MessageDto> wrap(List<Message> messages) {
        return messages.stream().map(MessageDto::new).collect(Collectors.toList());
    }
}
