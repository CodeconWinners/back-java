package dev.desabafa.model.dto.response.user.calendar;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.desabafa.model.enums.MeetingStatusEnum;
import dev.desabafa.model.enums.RatingMeetingEnum;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MeetingDetailsResponse(
    String title,
    LocalDate date,
    String time,
    int duration,
    String description,
    String predictionMessage,
    String transcriptionMessage,
    RatingMeetingEnum predictionRating,
    RatingMeetingEnum transcriptionRating,
    MeetingStatusEnum status
) {}
