package dev.desabafa.model.dto.response.user.calendar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public final class MeetingResponse {

    private final String id;
    private final LocalDateTime createdTime;
    private final LocalDateTime updatedTime;
    private final MeetingDetailsResponse details;

    public MeetingResponse(
        String id,
        LocalDateTime createdTime,
        LocalDateTime updatedTime,
        MeetingDetailsResponse details

    ) {
        this.id = id;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.details = details;
    }

    public String id() {
        return id;
    }

    public LocalDateTime createdTime() {
        return createdTime;
    }

    public LocalDateTime updatedTime() {
        return updatedTime;
    }

    public MeetingDetailsResponse details() {
        return details;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (MeetingResponse) obj;
        return Objects.equals(this.id, that.id) &&
            Objects.equals(this.createdTime, that.createdTime) &&
            Objects.equals(this.updatedTime, that.updatedTime) &&
            Objects.equals(this.details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdTime, updatedTime, details);
    }

    @Override
    public String toString() {
        return "MeetingResponse[" +
            "id=" + id + ", " +
            "createdTime=" + createdTime + ", " +
            "updatedTime=" + updatedTime + ", " +
            "details=" + details + ']';
    }
}
