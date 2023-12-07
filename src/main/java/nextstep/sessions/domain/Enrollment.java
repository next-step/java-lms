package nextstep.sessions.domain;

import nextstep.sessions.strategy.EnrollmentStrategy;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private Status status;
    private final List<NsUser> users;
    private final EnrollmentStrategy enrollmentStrategy;

    public Enrollment(EnrollmentStrategy enrollmentStrategy) {
        this.status = initSessionStatus();
        this.users = initUsers();
        this.enrollmentStrategy = enrollmentStrategy;
    }

    private List<NsUser> initUsers() {
        return new ArrayList<>();
    }

    private Status initSessionStatus() {
        return Status.PREPARE;
    }

    public void recruiting(){
        this.status = Status.RECRUITING;
    }

    public void changeSessionStatus(Status status) {
        this.status = status;
    }

    public void enroll(NsUser user, int payMoney){
        isEnrolmentPossible();
        enrollmentStrategy.enrollment(payMoney);
        this.users.add(user);
    }

    public void isEnrolmentPossible() {
        if (!status.equals(Status.RECRUITING)) {
            throw new IllegalArgumentException("강의가 모집중이 아닙니다.");
        }
    }
}
