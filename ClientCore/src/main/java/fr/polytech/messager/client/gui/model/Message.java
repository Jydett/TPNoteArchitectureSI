package fr.polytech.messager.client.gui.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private Long id;
    private String author;
    private String content;
}
