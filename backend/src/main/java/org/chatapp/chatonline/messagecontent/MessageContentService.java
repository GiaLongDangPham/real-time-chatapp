package org.chatapp.chatonline.messagecontent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<MessageContentDTO> getMessagesByRoomId(UUID roomId) {
        return messageContentRepository.findByMessageRoomIdOrderByDateSent(roomId)
                .stream().map(messageContentMapper::toDTO).toList();
    }

    public MessageContentDTO save(final MessageContentDTO messageContentDTO) {
        final MessageContent messageContent = messageContentRepository.save(messageContentMapper.toEntity(messageContentDTO));
        return messageContentMapper.toDTO(messageContent);
    }
}
