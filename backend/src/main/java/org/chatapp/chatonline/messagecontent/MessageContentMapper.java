package org.chatapp.chatonline.messagecontent;

import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import org.chatapp.chatonline.messageroom.MessageRoomRepository;
import org.chatapp.chatonline.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageContentMapper {
    private final MessageRoomRepository messageRoomRepository;
    private final UserRepository userRepository;

    public MessageContentDTO toDTO(MessageContent messageContent) {
        return MessageContentDTO.builder()
                .id(messageContent.getId())
                .content(messageContent.getContent())
                .dateSent(messageContent.getDateSent())
                .messageType(messageContent.getMessageType())
                .messageRoomId(messageContent.getMessageRoom().getId())
                .sender(messageContent.getUser().getUsername())
                .build();
    }

    public MessageContent toEntity(MessageContentDTO messageContentDTO) {
        return MessageContent.builder()
                .id(messageContentDTO.getId())
                .content(messageContentDTO.getContent())
                .dateSent(messageContentDTO.getDateSent())
                .messageType(messageContentDTO.getMessageType())
                .messageRoom(
                        messageContentDTO.getMessageRoomId() == null ?
                                null
                                : messageRoomRepository.findById(messageContentDTO.getMessageRoomId())
                                        .orElseThrow(() -> new EntityNotFoundException("Message room not found"))
                )
                .user(
                        messageContentDTO.getSender() == null ?
                                null
                                : userRepository.findById(messageContentDTO.getSender())
                                        .orElseThrow(() -> new EntityNotFoundException("User not found"))
                )
                .build();
    }
}
