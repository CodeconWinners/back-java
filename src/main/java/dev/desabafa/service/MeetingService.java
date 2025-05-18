package dev.desabafa.service;

import dev.desabafa.client.MeetingsClient;
import dev.desabafa.gaming.GameEngine;
import dev.desabafa.model.dto.response.user.calendar.MeetingResponse;
import dev.desabafa.model.dto.response.user.calendar.UserMeetingsResponse;
import dev.desabafa.model.entity.Meeting;
import dev.desabafa.model.entity.UserProfile;
import dev.desabafa.model.mapper.MeetingMapper;
import dev.desabafa.repository.MeetingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final MeetingsClient meetingsClient;

    public UserMeetingsResponse getMeetingsResponse(String meetingId) {
        log.info("Fetching meeting details for meetingId: {}", meetingId);
        return meetingsClient.getMeetingDetails(meetingId);
    }

    public List<Meeting> getAllMeetingsByUserProfile(UserProfile userProfile) {
        log.info("Fetching all meetings for userId: {}", userProfile.getUserId());
        return meetingRepository.getAllByUserProfile(userProfile);
    }

}
