package dev.desabafa.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum MeetingStatusEnum {
    CONFIRMED(1),
    DECLINED(2),
    TENTATIVE(3);

    @JsonValue
    private final int value;

    MeetingStatusEnum(int value) {
        this.value = value;
    }

    public static MeetingStatusEnum fromValue(int value) {
        return Arrays.stream(values())
            .filter(m -> m.value == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid Status value: " + value));
    }
}
