package dev.desabafa.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum RatingMeetingEnum {
    MUITO_UTIL(1),
    UTIL(2),
    INUTIL(3),
    IMPRATICAVEL(4);

    @JsonValue
    private final int value;

    RatingMeetingEnum(int value) {
        this.value = value;
    }

    public static RatingMeetingEnum fromValue(int value) {
        return Arrays.stream(values())
            .filter(r -> r.value == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid Rating value: " + value));
    }
}