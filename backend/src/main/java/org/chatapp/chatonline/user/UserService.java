package org.chatapp.chatonline.user;

import lombok.RequiredArgsConstructor;
import org.chatapp.chatonline.utils.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDTO login(final UserDTO userDTO) {
        final User user = userRepository.findById(userDTO.getUsername())
                .orElseGet(() -> createUser(userDTO));

        validatePassword(userDTO, user.getPassword());

        return userMapper.toDTO(user);
    }


    private void validatePassword(UserDTO userDTO, String password) {
        if(!userDTO.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
    }


    private User createUser(UserDTO userDTO) {
        final User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .status(UserStatus.ONLINE)
                .lastLogin(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    public UserDTO connect(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getUsername());
        user.ifPresent(u -> {
            u.setStatus(UserStatus.ONLINE);
            userRepository.save(u);
        });
        return user.map(userMapper::toDTO).orElse(null);
    }

    public List<UserDTO> getOnlineUsers() {
        return userRepository.findAllByStatus(UserStatus.ONLINE)
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }



    public UserDTO logout(final String username) {
        Optional<User> user = userRepository.findById(username);
        user.ifPresent(u -> {
            u.setStatus(UserStatus.OFFLINE);
            u.setLastLogin(LocalDateTime.now());
            userRepository.save(u);
        });
        return user.map(userMapper::toDTO).orElse(null);
    }

    public List<UserDTO> searchUsersByUsername(String username) {
        return userRepository.findAllByUsernameContainingIgnoreCase(username)
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public UserDTO uploadAvatar(final MultipartFile file, final String username) {
        final Optional<User> user = userRepository.findById(username);

        if(user.isPresent())  {
            if(user.get().getAvatarUrl() != null) {
                // delete
                FileUtils.deleteFile("/" + FileUtils.FOLDER_AVATAR + "/" + user.get().getAvatarShortUrl());
            }
            // upload
            String avatarUrl = FileUtils.storeFile(file, FileUtils.FOLDER_AVATAR);
            user.get().setAvatarUrl(avatarUrl);
            userRepository.save(user.get());
        }
        return user.map(userMapper::toDTO).orElse(null);
    }
}
