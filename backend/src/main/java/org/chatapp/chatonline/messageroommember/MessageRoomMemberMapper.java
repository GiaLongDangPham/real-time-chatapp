package org.chatapp.chatonline.messageroommember;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.chatapp.chatonline.messageroom.MessageRoom;
import org.chatapp.chatonline.messageroom.MessageRoomDTO;
import org.chatapp.chatonline.messageroom.MessageRoomMapper;
import org.chatapp.chatonline.messageroom.MessageRoomRepository;
import org.chatapp.chatonline.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageRoomMemberMapper {
    private final UserRepository userRepository;
    private final MessageRoomRepository messageRoomRepository;

    public MessageRoomMemberDTO toDTO(MessageRoomMember member) {
        return MessageRoomMemberDTO.builder()
                .messageRoomId(member.getMessageRoom().getId())
                .username(member.getUser().getUsername())
                .isAdmin(member.getIsAdmin())
                .lastSeen(member.getLastSeen())
                .lastLogin(member.getUser().getLastLogin())
                .build();
    }

    public MessageRoomMember toEntity(MessageRoomMemberDTO dto) {
        return MessageRoomMember.builder()
                .isAdmin(dto.getIsAdmin())
                .lastSeen(dto.getLastSeen())
                .messageRoom(
                        dto.getMessageRoomId() == null ? null
                                : messageRoomRepository.findById(dto.getMessageRoomId())
                                        .orElseThrow(() -> new EntityNotFoundException("Message room not found"))
                )
                .user(
                        dto.getUsername() == null ? null
                                : userRepository.findById(dto.getUsername())
                                            .orElseThrow(() -> new EntityNotFoundException("User not found"))
                )
                .build();
    }
}
