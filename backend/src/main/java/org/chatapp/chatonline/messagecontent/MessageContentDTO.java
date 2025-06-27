package org.chatapp.chatonline.messagecontent;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageContentDTO {
    private UUID id;

    private String content;

    private LocalDateTime dateSent;

    private MessageType messageType;

    private UUID messageRoomId;

    private String sender;
}
