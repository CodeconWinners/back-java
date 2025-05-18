package dev.desabafa.service;

import dev.desabafa.model.dto.UserDto;
import dev.desabafa.model.entity.UserProfile;
import dev.desabafa.model.mapper.UserMapper;
import dev.desabafa.repository.UserProfileRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserMapper userMapper;
    private final GamingService gamingService;

    public UserProfile getUserProfile(UUID userId) {
        log.info("Fetching user profile for userId: {}", userId);
        var userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User profile not found"));
        gamingService.updateUserLevel(userProfile);
        return userProfile;
    }

    public void saveUserProfile(UserDto userDto) {
        log.info("Saving user profile for userId: {}", userDto.getUserId());
        if (userDto.getTotalXp() == null) {
            userDto.setTotalXp(0);
        }
        if (userDto.getLevel() == null) {
            userDto.setLevel(1);
        }
        var userProfile = userMapper.toEntity(userDto);
        userProfileRepository.save(userProfile);
    }

    public void updateUser(UserDto userDto) {
        log.info("Updating user profile for userId: {}", userDto.getUserId());
        var userProfile = userProfileRepository.findById(UUID.fromString(userDto.getUserId()))
                .orElseThrow(() -> new RuntimeException("User profile not found"));
        userMapper.updateFromDto(userDto, userProfile);
        userProfileRepository.save(userProfile);
    }
}
