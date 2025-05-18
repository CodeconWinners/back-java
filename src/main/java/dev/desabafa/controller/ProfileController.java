package dev.desabafa.controller;

import static org.springframework.http.ResponseEntity.ok;

import dev.desabafa.model.dto.UserDto;
import dev.desabafa.model.mapper.UserMapper;
import dev.desabafa.service.UserProfileService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final UserProfileService userProfileService;
    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getProfile(@PathVariable String userId) {
        log.info("Fetching profile for userId: {}", userId);
        return ok(userMapper.toDto(userProfileService.getUserProfile(UUID.fromString(userId))));
    }

    @PostMapping
    public ResponseEntity<UserDto> saveProfile(@RequestBody UserDto userDto) {
        log.info("Saving profile: {}", userDto);
        userProfileService.saveUserProfile(userDto);
        return ok(userDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateProfile(@PathVariable String userId, @RequestBody UserDto userDto) {
        log.info("Updating profile: {}", userDto);
        userProfileService.updateUser(userDto);
        return ok(userDto);
    }


}
