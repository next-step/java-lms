package nextstep.courses.domain.session;

import nextstep.courses.SessionUnregistrableException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

/**
 * 강의 정원 및 현재 등록 인원을 관리하는 도메인
 */
public class SessionEnrollment {
    private final int maxCapacity;
    private final NsUsers enrolledUsers;

    public SessionEnrollment() {
        this(-1, new NsUsers());
    }

    public SessionEnrollment(int maxCapacity) {
        this(maxCapacity, new NsUsers());
    }

    public SessionEnrollment(int maxCapacity, NsUsers enrolledUsers) {
        this.maxCapacity = maxCapacity;
        this.enrolledUsers = enrolledUsers;
    }

    public void enroll(NsUser user) {
        if (!hasCapacity()) {
            throw new SessionUnregistrableException("정원 초과로 수강신청할 수 없습니다.");
        }
        this.enrolledUsers.addUser(user);
    }

    private boolean hasCapacity() {
        if (maxCapacity == -1) {
            return true;
        }
        return enrolledUsers.hasLessThan(maxCapacity);
    }
}
