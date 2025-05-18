package dev.desabafa.controller;

import dev.desabafa.service.GamingService;
import dev.desabafa.service.UserProfileService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gaming")
@Slf4j
@RequiredArgsConstructor
public class GamingController {

    private final GamingService gamingService;
    private final UserProfileService userProfileService;

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void increaseQuestXp(@PathVariable String userId) {
        log.info("Increasing quest XP for userId: {}", userId);
        gamingService.increaseQuestXp(userProfileService.getUserProfile(UUID.fromString(userId)));

    }

}
