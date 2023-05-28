package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Student {
    private Long id;

    private Session session;

    private NsUser user;

    private LocalDateTime createdAt;

    private Long creatorId;

    public Student(Session session, NsUser user) {
        this.session = session;
        this.user = user;
    }
}
