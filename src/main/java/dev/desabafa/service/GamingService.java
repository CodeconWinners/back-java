package dev.desabafa.service;

import dev.desabafa.gaming.GameEngine;
import dev.desabafa.model.dto.response.user.calendar.MeetingResponse;
import dev.desabafa.model.dto.response.user.calendar.UserMeetingsResponse;
import dev.desabafa.model.entity.Meeting;
import dev.desabafa.model.entity.UserProfile;
import dev.desabafa.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GamingService implements ApplicationContextAware {

    private ApplicationContext ctx;
    private final MeetingService meetingService;
    private final UserMapper userMapper;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        this.ctx = ctx;
    }

        public Long getCurrentLevel(UserMeetingsResponse userMeetingsResponse) {
        var finalXp = userMeetingsResponse.meetingResponses().stream()
            .map(MeetingResponse::details)
            .map(meeting -> GameEngine.calculateXpPenalty(meeting.status(),
                meeting.transcriptionRating() == null ?
            meeting.predictionRating() : meeting.transcriptionRating()))
            .reduce(0L, Long::sum);
        return GameEngine.calculateLevel(finalXp);
    }

    public void updateUserLevel(UserProfile userProfile) {
        log.info("Updating user level for userId: {}", userProfile.getUserId());

        var allUserMeetings = meetingService.getMeetingsResponse(userProfile.getUserId().toString());
        var savedMeetings = meetingService.getAllMeetingsByUserProfile(userProfile);
        var notAccountedMeetings = allUserMeetings.meetingResponses().stream()
            .filter(meeting -> !savedMeetings.stream()
                .map(Meeting::getMeetingId).toList().contains(meeting.id()))
            .toList();

        var updatedXp = allUserMeetings.meetingResponses().stream().map(MeetingResponse::details)
            .map(details -> GameEngine.calculateXpPenalty(details.status(),
                details.transcriptionRating() == null ?
                    details.predictionRating() : details.transcriptionRating()))
            .reduce(0L, Long::sum);
        if (userProfile.getQuestXp() == null) {
            userProfile.setQuestXp(0L);
        }
        userProfile.setTotalXp(updatedXp + userProfile.getQuestXp());
        var newXp = notAccountedMeetings.stream().map(MeetingResponse::details).map(details ->
            GameEngine.calculateXpPenalty(details.status(), details.transcriptionRating() == null ?
                details.predictionRating() : details.transcriptionRating())).reduce( 0L, Long::sum);

        userProfile.addXp(newXp);
    }

    public void increaseQuestXp(UserProfile userProfile) {
        log.info("Increasing quest XP for userId: {}", userProfile.getUserId());
        var randomModifier = (Math.random() * 100L);
        if (userProfile.getQuestXp() == null) {
            userProfile.setQuestXp(0L);
        }
        var userService = ctx.getBean(UserProfileService.class);
        userProfile.addQuestXp((long) randomModifier);
        userService.saveUserProfile(userMapper.toDto(userProfile));
    }
}
