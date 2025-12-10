package nextstep.courses.domain.session;

import nextstep.courses.SessionUnregistrableException;
import nextstep.users.domain.NsUsers;

/**
 * 강의 정원 및 현재 등록 인원을 관리하는 도메인
 */
public class SessionEnrollment {
    private final Capacity maxCapacity;
    private final NsUsers enrolledUsers;
    private final Session session;

    public SessionEnrollment(Session session) {
        this(new Capacity(), new NsUsers(), session);
    }

    public SessionEnrollment(int maxCapacity, Session session) {
        this(new Capacity(maxCapacity), new NsUsers(), session);
    }

    public SessionEnrollment(Capacity maxCapacity, NsUsers enrolledUsers, Session session) {
        this.maxCapacity = maxCapacity;
        this.enrolledUsers = enrolledUsers;
        this.session = session;
    }

    public void enroll(EnrollmentCondition condition) {
        checkEnrollable(condition);
        this.enrolledUsers.addUser(condition.getUser());
    }

    private void checkEnrollable(EnrollmentCondition condition) {
        if (!session.isRecruiting()) {
            throw new SessionUnregistrableException(String.format("%s 상태인 강의는 수강신청할 수 없습니다.", session.currentStatusToHumanReadable()));
        }

        if (!session.canEnroll(condition)) {
            throw new SessionUnregistrableException(String.format("%s 강의 수강 신청 조건 미달로 신청할 수 없습니다.", session.typeToHumanReadable()));
        }

        if (!hasCapacity()) {
            throw new SessionUnregistrableException("정원 초과로 수강신청할 수 없습니다.");
        }
    }

    private boolean hasCapacity() {
        if (maxCapacity.isUnlimited()) {
            return true;
        }
        return enrolledUsers.isLessThan(maxCapacity);
    }
}
