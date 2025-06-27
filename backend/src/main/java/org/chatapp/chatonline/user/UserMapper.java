package org.chatapp.chatonline.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .status(user.getStatus())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }

    public User toEntity(UserDTO dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .status(dto.getStatus())
                .avatarUrl(dto.getAvatarUrl())
                .build();
    }
}
