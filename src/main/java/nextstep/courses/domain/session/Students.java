package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Students {
    private final int capacity;

    private SessionFeeType sessionFeeType;

    private SessionStatus sessionStatus;

    private final List<NsUser> users = new ArrayList<>();

    public Students(int capacity, SessionFeeType sessionFeeType, SessionStatus sessionStatus) {
        validateCapacity(capacity);
        Objects.requireNonNull(sessionFeeType);
        Objects.requireNonNull(sessionStatus);
        this.capacity = capacity;
        this.sessionFeeType = sessionFeeType;
        this.sessionStatus = sessionStatus;
    }

    private void validateCapacity(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("강의 최대 수강인원은 1보다 커야 합니다.");
        }
    }

    public void addStudent(NsUser nsUser) {
        isAbleToRegister(nsUser);
        users.add(nsUser);
    }

    private void isAbleToRegister(NsUser nsUser) {
        if (!sessionStatus.equals(SessionStatus.RECRUITING)) {
            throw new IllegalStateException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
        if (users.size() >= capacity) {
            throw new IllegalStateException("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
        }
        if (users.contains(nsUser)) {
            throw new IllegalStateException("이미 등록된 학생입니다.");
        }
    }

    public void startRecruit() {
        this.sessionStatus = SessionStatus.RECRUITING;
    }
}
