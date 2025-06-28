package org.chatapp.chatonline.messageroom;

import lombok.RequiredArgsConstructor;
import org.chatapp.chatonline.messagecontent.MessageContent;
import org.chatapp.chatonline.messageroommember.MessageRoomMember;
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

@Service
@RequiredArgsConstructor
public class MessageRoomService {

    private final MessageRoomRepository messageRoomRepository;
    private final MessageRoomMapper messageRoomMapper;
    private final UserRepository userRepository;

    public MessageRoomDTO findMessageRoomByMembers(List<String> members) {
        return messageRoomRepository.findMessageRoomByMembers(members, members.size())
                .map(messageRoomMapper::toDTO)
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

        //temp
        MessageContent messageContent = MessageContent.builder()
                .content("Hi")
                .dateSent(LocalDateTime.now())
                .messageRoom(messageRoom)
                .user(user)
                .build();

        if(messageRoom.getMessageContents() == null) {
            messageRoom.setMessageContents(new ArrayList<>());
        }
        messageRoom.getMessageContents().add(messageContent);

        MessageRoom saved = messageRoomRepository.save(messageRoom);

        return messageRoomMapper.toDTO(saved);
    }



    public List<MessageRoomDTO> findMessageRoomAtLeastOneContent(final String username) {
        return messageRoomRepository.findMessageRoomAtLeastOneContent(username)
                .stream()
                .map(messageRoomMapper::toDTO)
                .toList();
    }
}
