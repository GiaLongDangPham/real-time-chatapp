package org.chatapp.chatonline.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;

    private String password;

    private UserStatus status;

    private String avatarUrl;
}
