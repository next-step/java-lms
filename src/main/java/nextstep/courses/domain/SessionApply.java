package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class SessionApply {

    private static final String NOT_RECRUITING = "수강 모집상태가 아닙니다.";
    private static final String CLOSED_RECRUITING = "수강 인원이 마감되었습니다.";

    private SessionStatus status = SessionStatus.PREPARING;

    private SessionUsers sessionUsers = new SessionUsers();

    private RecruitmentCount recruitmentCount;

    public SessionApply(int recruitmentCount) {
        this.recruitmentCount = new RecruitmentCount(recruitmentCount);
    }

    public void recruiting() {
        status = SessionStatus.RECRUITING;
    }

    public SessionUser apply(Long id, Session session, NsUser user) {
        if (status.isNotRecruiting()) {
            throw new IllegalStateException(NOT_RECRUITING);
        }
        if (sessionUsers.isClosed(recruitmentCount)) {
            throw new IllegalStateException(CLOSED_RECRUITING);
        }
        return sessionUsers.apply(id, session, user);
    }

}
