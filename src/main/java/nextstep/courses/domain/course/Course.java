package nextstep.courses.domain.course;

import lombok.Builder;
import lombok.Getter;
import nextstep.courses.domain.session.Session;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Course {

    private Long id;

    private String title;

    private Long creatorId;

    private List<Session> sessions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void addSingleSession(Session session) {
        sessions.add(session);
    }

}
