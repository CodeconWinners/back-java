package dev.desabafa.model.dto.response.user.calendar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserMeetingsResponse(@JsonProperty("items")
                                   List<MeetingResponse> meetingResponses) {

}
