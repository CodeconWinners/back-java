package dev.desabafa.client;

import dev.desabafa.model.dto.response.user.calendar.UserMeetingsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class MeetingsClient {

    private final RestClient restClient;

    public UserMeetingsResponse getMeetingDetails(String userId) {
        return restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/calendar/read-events")
                .queryParam("userId", userId)
                .build())
            .retrieve()
            .body(UserMeetingsResponse.class);
    }
}
