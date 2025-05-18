package dev.desabafa.gaming;

import dev.desabafa.model.enums.MeetingStatusEnum;
import dev.desabafa.model.enums.RatingMeetingEnum;

public class GameEngine {

    private static final Long BASE_PENALTY = 10L;

    private GameEngine() {
    }

    public static Long calculateXpPenalty(MeetingStatusEnum status, RatingMeetingEnum rating) {
        if (status != MeetingStatusEnum.CONFIRMED || rating.getValue() <= 2) {
            return 0L;
        }
        return BASE_PENALTY * (int) Math.pow((rating.getValue() - 1), 2);
    }

    public static Long calculateLevel(Long totalXp) {
        Long level = 1L;
        while (totalXp >= xpRequiredForLevel(level + 1L)) {
            level++;
        }
        return level;
    }

    private static int xpRequiredForLevel(Long level) {
        return (int) (100 * Math.pow(level, 1.5));
    }

}
