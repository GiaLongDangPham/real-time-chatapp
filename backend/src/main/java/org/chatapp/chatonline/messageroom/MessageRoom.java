package org.chatapp.chatonline.messageroom;

import jakarta.persistence.*;
import lombok.*;
import org.chatapp.chatonline.messagecontent.MessageContent;
import org.chatapp.chatonline.messageroommember.MessageRoomMember;
import org.chatapp.chatonline.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message_room")
@EntityListeners(AuditingEntityListener.class)
public class MessageRoom {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private Boolean isGroup;

    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private User createdBy;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    private List<MessageRoomMember> members;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    private List<MessageContent> messageContents;

}