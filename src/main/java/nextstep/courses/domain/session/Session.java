package nextstep.courses.domain.session;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public class Session {

    private final Long id;
    private final Course course;
    private final SessionCoverImage coverImage;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(@NonNull Long id,
                   @NonNull Course course,
                   @NonNull SessionCoverImage coverImage) {
        this.id = id;
        this.course = course;
        this.coverImage = coverImage;
        this.createdAt = LocalDateTime.now();
    }

    public void enroll(SessionEnrollment enrollment, NsUser user) {
        enrollment.enroll(user);
    }

}
