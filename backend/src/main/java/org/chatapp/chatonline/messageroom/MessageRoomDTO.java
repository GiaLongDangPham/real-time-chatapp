package org.chatapp.chatonline.messageroom;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRoomDTO {
    private UUID id;

    private String name;

    private Boolean isGroup;

    private LocalDateTime createdDate;

    private String createdById;

}