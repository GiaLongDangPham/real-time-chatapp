package org.chatapp.chatonline.messageroom;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.chatapp.chatonline.messagecontent.MessageContent;
import org.chatapp.chatonline.messagecontent.MessageContentDTO;
import org.chatapp.chatonline.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageRoomMapper {
    private final UserRepository userRepository;

    public MessageRoomDTO toDTO(MessageRoom messageRoom) {
        return MessageRoomDTO.builder()
                .id(messageRoom.getId())
                .name(messageRoom.getName())
                .isGroup(messageRoom.getIsGroup())
                .createdDate(messageRoom.getCreatedDate())
                .createdById(messageRoom.getCreatedBy().getUsername())
                .build();
    }

    public MessageRoom toEntity(MessageRoomDTO messageRoomDTO) {
        return MessageRoom.builder()
                .id(messageRoomDTO.getId())
                .name(messageRoomDTO.getName())
                .isGroup(messageRoomDTO.getIsGroup())
                .createdDate(messageRoomDTO.getCreatedDate())
                .createdBy(
                        messageRoomDTO.getCreatedById() == null ?
                                null
                                : userRepository.findById(messageRoomDTO.getCreatedById())
                                    .orElseThrow(() -> new EntityNotFoundException("User not found"))
                )
                .build();
    }
}
