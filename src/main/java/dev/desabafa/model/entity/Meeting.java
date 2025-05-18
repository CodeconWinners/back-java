package dev.desabafa.model.entity;

import dev.desabafa.model.enums.MeetingStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {

    @Id
    @Column(name = "meeting_id", nullable = false, updatable = false)
    private String meetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;

    @Column(name = "created_time", nullable = false)
    private OffsetDateTime createdTime;

    @Column(name = "updated_time", nullable = false)
    private OffsetDateTime updatedTime;

    @Column(name = "meeting_date", nullable = false)
    private OffsetDateTime date;

    @Column(name = "meeting_time", nullable = false)
    private String time;

    @Column(name = "duration_seconds", nullable = false)
    private int duration;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "status", nullable = false)
    private MeetingStatusEnum status;

    @Column(name = "prediction_message", length = 2000)
    private String predictionMessage;

    @Column(name = "transcription_message", length = 2000)
    private String transcriptionMessage;
}
