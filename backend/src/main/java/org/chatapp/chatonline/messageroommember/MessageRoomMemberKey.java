package org.chatapp.chatonline.messageroommember;

import lombok.Data;
import org.chatapp.chatonline.messageroom.MessageRoom;
import org.chatapp.chatonline.user.User;

import java.io.Serializable;

@Data
public class MessageRoomMemberKey implements Serializable {
    private User user;
    private MessageRoom messageRoom;

}