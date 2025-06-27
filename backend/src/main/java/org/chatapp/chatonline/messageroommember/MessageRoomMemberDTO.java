package org.chatapp.chatonline.messageroommember;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRoomMemberDTO {
    private UUID messageRoomId;

    private String user;

    private Boolean isAdmin;

    private LocalDateTime lastSeen;

    private LocalDateTime lastLogin;

}