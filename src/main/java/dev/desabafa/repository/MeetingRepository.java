package dev.desabafa.repository;

import dev.desabafa.model.entity.Meeting;
import dev.desabafa.model.entity.UserProfile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, String> {

    List<Meeting> getAllByUserProfile(UserProfile userProfile);
}
