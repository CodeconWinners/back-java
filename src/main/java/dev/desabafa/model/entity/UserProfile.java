package dev.desabafa.model.entity;

import dev.desabafa.gaming.GameEngine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;
    private String name;
    private String lastName;
    private String email;
    private String jobTitle;
    private String jobDescription;
    private Long questXp = 0L;
    @Column(name = "total_xp", nullable = false)
    private Long totalXp = 0L;
    @Column(name = "level", nullable = false)
    private Long level = 1L;
    @OneToMany(
        mappedBy = "userProfile",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Meeting> meetings = new ArrayList<>();

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
        meeting.setUserProfile(this);
    }

    public void addXp(Long newXp) {
        this.totalXp += newXp;
        if (GameEngine.calculateLevel(totalXp) > this.level) {
            this.level = GameEngine.calculateLevel(totalXp);
        }
    }

    public void addQuestXp(Long newXp) {
        this.questXp += newXp;
        if (GameEngine.calculateLevel(questXp) > this.level) {
            this.level = GameEngine.calculateLevel(questXp);
        }
    }
}
