package nextstep.courses.domain;

import nextstep.courses.domain.base.BaseDate;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session extends BaseDate {

    private static final String NOT_RECRUITING = "수강 모집상태가 아닙니다.";

    private Long id;

    private Course course;

    private SessionDate sessionDate;

    private String imageUrl;

    private boolean paid;

    private SessionStatus status = SessionStatus.PREPARING;

    private SessionApplies sessionApplies = new SessionApplies();

    private RecruitmentCount recruitmentCount;

    public static Session open(Long id, Course course,
                               LocalDate sessionStartDate,
                               LocalDate sessionEndDate,
                               String imageUrl,
                               boolean paid,
                               int recruitmentCount) {
        return new Session(id, course,
                new SessionDate(sessionStartDate, sessionEndDate),
                imageUrl, paid, recruitmentCount);
    }

    public Session(Long id, Course course, SessionDate sessionDate,
                   String imageUrl, boolean paid, int recruitmentCount) {
        this.id = id;
        this.course = course;
        this.sessionDate = sessionDate;
        this.imageUrl = imageUrl;
        this.paid = paid;
        this.recruitmentCount = new RecruitmentCount(recruitmentCount);
    }

    public void apply(NsUser user) {
        if (status.isNotRecruiting()) {
            throw new IllegalStateException(NOT_RECRUITING);
        }
        sessionApplies.apply(this, user);
    }

    public void recruiting() {
        status = SessionStatus.RECRUITING;
    }

    public boolean isClosed(int currentCount) {
        return recruitmentCount.isClosed(currentCount);
    }



}
