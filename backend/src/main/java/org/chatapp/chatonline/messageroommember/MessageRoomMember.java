package org.chatapp.chatonline.messageroommember;

import jakarta.persistence.*;
import lombok.*;
import org.chatapp.chatonline.messageroom.MessageRoom;
import org.chatapp.chatonline.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "message_room_member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MessageRoomMemberKey.class)
public class MessageRoomMember {
    @Id
    @ManyToOne
    @JoinColumn(name = "message_room_id")
    private MessageRoom messageRoom;

    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    private Boolean isAdmin;

    private LocalDateTime lastSeen;

}