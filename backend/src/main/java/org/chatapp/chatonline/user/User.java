package org.chatapp.chatonline.user;

import jakarta.persistence.*;
import lombok.*;
import org.chatapp.chatonline.messagecontent.MessageContent;
import org.chatapp.chatonline.messageroom.MessageRoom;
import org.chatapp.chatonline.messageroommember.MessageRoomMember;
import org.chatapp.chatonline.utils.FileUtils;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private LocalDateTime lastLogin = LocalDateTime.now();

    private String avatarUrl;

    @OneToMany(mappedBy = "createdBy")
    private List<MessageRoom> messageRooms;

    @OneToMany(mappedBy = "user")
    private List<MessageRoomMember> messageRoomMembers;

    @OneToMany(mappedBy = "user")
    private List<MessageContent> messageContents;

    public String getAvatarUrl() {
        if(avatarUrl == null) return null;
        return FileUtils.getAvatarUrl(avatarUrl);
    }

    public String getAvatarShortUrl() {
        return avatarUrl;
    }
}
