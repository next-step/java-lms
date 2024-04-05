package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Session {
    protected final Long idx;
    private final Course course;
    private final Period period;
    private final Image image;
    private final Status status;
    private final Type type;
    protected final NsUsers nsUsers;

    public Session(Course course, Period period, Image image, NsUsers users, Type type) {
        this(0L, course, period, image, Status.READY, users, type);
    }

    public Session(Long idx, Course course, Period period, Image image, Status status, NsUsers nsUsers, Type type) {
        this.idx = idx;
        this.course = course;
        this.period = period;
        this.image = image;
        this.status = status;
        this.nsUsers = nsUsers;
        this.type = type;
    }

    public void enroll(NsUser nsUser) {
        if (!canEnrollStatus()) {
            throw new IllegalArgumentException("강의는 현재 모집하고 있지 않습니다.");
        }
        assertCanEnroll();
        nsUsers.add(nsUser);
    }

    public abstract void assertCanEnroll();

    private boolean canEnrollStatus() {
        return this.status == Status.RECRUITING;
    }

    public boolean equalsType(Type type) {
        if (type == null) {
            return false;
        }
        return this.type == type;
    }

    public static class Period {
        private final LocalDate startDate;
        private final LocalDate endDate;

        public Period(LocalDate startDate, LocalDate endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public LocalDate startDate() {
            return startDate;
        }

        public LocalDate endDate() {
            return endDate;
        }
    }

    public enum Status {
        READY,
        RECRUITING,
        CLOSED
    }

    public enum Type {
        FREE, PAID
    }
}
