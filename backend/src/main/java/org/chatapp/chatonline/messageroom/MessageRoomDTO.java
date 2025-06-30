package org.chatapp.chatonline.messageroom;

import lombok.*;
import org.chatapp.chatonline.messagecontent.MessageContentDTO;
import org.chatapp.chatonline.messageroommember.MessageRoomMemberDTO;

import java.time.LocalDateTime;
import java.util.List;
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

    private MessageContentDTO lastMessage;

    private List<MessageRoomMemberDTO> members;

    private Long unseenCount;
}