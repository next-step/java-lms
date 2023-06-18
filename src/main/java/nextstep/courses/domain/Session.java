package nextstep.courses.domain;

import lombok.Builder;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class Session {

    private Long id;
    private Long courseId;
    private String title;
    private Period period;
    private String imageUrl;
    private SessionType sessionType;
    private SessionValidator sessionValidator;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long creatorId;

    public Session(Long id, String title, int maxCount, Period period, String imageUrl, SessionState sessionState,
            SessionType sessionType) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionValidator = sessionValidator;
        this.period = period;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.imageUrl = imageUrl;
        this.sessionType = sessionType;
        this.createAt = LocalDateTime.now();
        this.creatorId = creatorId;
    }

    public void apply(NsUser loginUser) {
        sessionValidator.addPerson(loginUser);
    }
}
