package org.chatapp.chatonline.messagecontent;

import lombok.RequiredArgsConstructor;
import org.chatapp.chatonline.messageroom.MessageRoom;
import org.chatapp.chatonline.messageroom.MessageRoomDTO;
import org.chatapp.chatonline.messageroom.MessageRoomMapper;
import org.chatapp.chatonline.messageroom.MessageRoomRepository;
import org.chatapp.chatonline.messageroommember.MessageRoomMember;
import org.chatapp.chatonline.user.User;
import org.chatapp.chatonline.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageContentService {

    private final MessageContentRepository messageContentRepository;
    private final MessageContentMapper messageContentMapper;



    public MessageContentDTO getLastMessage(final UUID messageRoomId) {
        return messageContentRepository.findTopByMessageRoomIdOrderByDateSentDesc(messageRoomId)
                .map(messageContentMapper::toDTO)
                .orElse(null);
    }

}
