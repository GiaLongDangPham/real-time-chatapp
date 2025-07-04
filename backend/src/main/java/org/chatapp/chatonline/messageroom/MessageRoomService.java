package org.chatapp.chatonline.messageroom;

import lombok.RequiredArgsConstructor;
import org.chatapp.chatonline.messagecontent.MessageContent;
import org.chatapp.chatonline.messagecontent.MessageContentDTO;
import org.chatapp.chatonline.messagecontent.MessageContentService;
import org.chatapp.chatonline.messageroommember.MessageRoomMember;
import org.chatapp.chatonline.messageroommember.MessageRoomMemberDTO;
import org.chatapp.chatonline.messageroommember.MessageRoomMemberService;
import org.chatapp.chatonline.user.User;
import org.chatapp.chatonline.user.UserDTO;
import org.chatapp.chatonline.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageRoomService {

    private final MessageRoomRepository messageRoomRepository;
    private final MessageRoomMapper messageRoomMapper;
    private final UserRepository userRepository;
    private final MessageContentService messageContentService;
    private final MessageRoomMemberService messageRoomMemberService;

    public MessageRoomDTO findMessageRoomByMembers(List<String> members) {
        return messageRoomRepository.findMessageRoomByMembers(members, members.size())
                .map(m -> {
                    MessageRoomDTO roomDTO = messageRoomMapper.toDTO(m);
                    List<MessageRoomMemberDTO> roomMembers = messageRoomMemberService.findByMessageRoomId(roomDTO.getId());
                    roomDTO.setMembers(roomMembers);
                    return roomDTO;
                })
                .orElse(null);
    }

    @Transactional
    public MessageRoomDTO create(final List<String> members, String username) {
        final User user = userRepository.findById(username).orElseThrow();

        MessageRoom messageRoom = MessageRoom.builder()
                .isGroup(members.size() > 2)
                .createdBy(user)
                .members(new ArrayList<>())
                .build();

        final List<User> users = userRepository.findAllByUsernameIn(members);

        users.forEach(u -> {
            final MessageRoomMember messageRoomMember = MessageRoomMember.builder()
                    .messageRoom(messageRoom)
                    .user(u)
                    .isAdmin(u.getUsername().equals(username))
                    .lastSeen(LocalDateTime.now())
                    .build();
            messageRoom.getMembers().add(messageRoomMember);
        });

        MessageRoom saved = messageRoomRepository.save(messageRoom);

        final MessageRoomDTO roomDTO = messageRoomMapper.toDTO(saved);
        final List<MessageRoomMemberDTO> roomMembers = messageRoomMemberService.findByMessageRoomId(roomDTO.getId());
        roomDTO.setMembers(roomMembers);
        return roomDTO;
    }



    public List<MessageRoomDTO> findMessageRoomAtLeastOneContent(final String username) {
        return messageRoomRepository.findMessageRoomAtLeastOneContent(username)
                .stream()
                .map(m -> {
                    final MessageRoomDTO roomDTO = messageRoomMapper.toDTO(m);

                    roomDTO.setUnseenCount(messageContentService.countUnseenMessage(m.getId(), username));

                    final MessageContentDTO lastMessage = messageContentService.getLastMessage(roomDTO.getId());
                    roomDTO.setLastMessage(lastMessage);

                    final List<MessageRoomMemberDTO> members = messageRoomMemberService.findByMessageRoomId(roomDTO.getId());
                    roomDTO.setMembers(members);

                    return roomDTO;
                })
                .toList();
    }

    public MessageRoomDTO findById(final UUID roomId) {
        return messageRoomRepository.findById(roomId)
                .map(room -> {
                    final MessageRoomDTO roomDTO = messageRoomMapper.toDTO(room);
                    final List<MessageRoomMemberDTO> roomMembers = messageRoomMemberService.findByMessageRoomId(roomDTO.getId());
                    roomDTO.setMembers(roomMembers);
                    return roomDTO;
                })
                .orElse(null);
    }
}
