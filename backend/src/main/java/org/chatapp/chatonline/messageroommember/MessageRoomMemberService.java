package org.chatapp.chatonline.messageroommember;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageRoomMemberService {

    private final MessageRoomMemberRepository messageRoomMemberRepository;
    private final MessageRoomMemberMapper messageRoomMemberMapper;



    public List<MessageRoomMemberDTO> findByMessageRoomId(final UUID messageRoomId) {
        return messageRoomMemberRepository.findByMessageRoomId(messageRoomId)
                .stream()
                .map(messageRoomMemberMapper::toDTO)
                .toList();
    }

    public MessageRoomMemberDTO updateLastSeen(final UUID roomId, final String username) {
        final MessageRoomMember member = messageRoomMemberRepository.findByMessageRoomIdAndUserUsername(roomId, username);
        member.setLastSeen(LocalDateTime.now());
        return messageRoomMemberMapper.toDTO(messageRoomMemberRepository.save(member));
    }

}