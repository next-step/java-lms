package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;

public class Session {

    private Tuition tuition;
    private Enrollment enrollment;
    private Period period;
    private CoverImage image;

    public Session(boolean isFree, int maxCount, int tuition) {
        this(new Tuition(isFree, tuition), new Enrollment(maxCount, new ArrayList<>()));
    }

    public Session(Tuition tuition, Enrollment enrollment) {
        this.tuition = tuition;
        this.enrollment = enrollment;
        this.period = new Period();
    }

    public Session(Tuition tuition, Enrollment enrollment, Period period, CoverImage image) {
        this.tuition = tuition;
        this.enrollment = enrollment;
        this.period = period;
        this.image = image;
    }

    public static Session createFull() {
        return new Session(new Tuition(false, 1000), Enrollment.createFull());
    }

    public boolean isLeft() {
        return enrollment.canEnroll();
    }

    public boolean canPay(Money submit) {
        return tuition.isEnough(submit);
    }

    public void register(NsUser user, Money submit) {
        if (tuition.canRegisterAsFree()) {
            enrollment.enroll(user);
        }

        if (!tuition.isEnough(submit)) {
            throw new IllegalArgumentException("금액이 부족하여 수강 신청을 진행할 수 없습니다.");
        }
        enrollment.enroll(user);
    }
}
