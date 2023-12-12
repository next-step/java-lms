package nextstep.courses.domain;

import nextstep.courses.type.MaxRegister;
import nextstep.courses.type.SessionDuration;
import nextstep.courses.type.SessionState;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.Objects;

import static nextstep.courses.type.SessionState.READY;

/**
 * 강의를 나타내는 객체
 * 가변 객체입니다.
 */
public class Session {
    private Long id;

    private Course course;

    private SessionState state;
    private RegisteredUsers registeredUsers;
    private SessionImage coverImage;
    private SessionDuration duration;

    private MaxRegister maxUserCount;
    private int fee;


    private Session() {

    }

    public static Session createFreeSession(Long id, Course course, SessionImage coverImage, SessionDuration duration) {
        Session session = new Session();

        session.id = id;
        session.course = course;
        session.state = READY;
        session.registeredUsers = new RegisteredUsers();
        session.coverImage = coverImage;
        session.duration = duration;
        session.maxUserCount = MaxRegister.infinite();
        session.fee = 0;

        validateSession(session);
        return session;
    }

    public static Session createPaidSession(Long id, Course course, SessionImage coverImage, SessionDuration duration, MaxRegister maxUserCount, int fee) {
        Session session = new Session();

        session.id = id;
        session.course = course;
        session.state = READY;
        session.registeredUsers = new RegisteredUsers();
        session.coverImage = coverImage;
        session.duration = duration;
        session.maxUserCount = maxUserCount;
        session.fee = fee;

        validateSession(session);
        return session;
    }

    private static void validateSession(Session session) {
        validateFee(session.fee);
    }
    private static void validateFee(int fee) {
        if (fee < 0) {
            throw new IllegalArgumentException("강의료가 음수일 수 없습니다.");
        }
    }

    private void baseRegisterUserMethod(NsUser user) {
        if (this.state != READY) {
            throw new IllegalStateException("사용자 등록은 모집중 상태에서만 가능하지만 모집중 상태가 아닙니다.");
        }
        registeredUsers.add(user);
    }

    public void registerUser(NsUser user) {
        if (fee > 0) {
            throw new IllegalArgumentException("유료 강의에 등록하려면 결제 정보가 있어야 합니다.");
        }

        this.baseRegisterUserMethod(user);
    }

    public void registerUser(NsUser user, Payment payment) {
        if (!payment.isSameUser(user)) {
            throw new IllegalArgumentException("지불 정보와 등록하는 유저가 일치하지 않습니다.");
        }

        if (!payment.isSameAmountWith(this.fee)) {
            throw new IllegalArgumentException("수강료와 지불 금액이 동일하지 않습니다.");
        }

        if (!this.maxUserCount.isLargerThan(this.registeredUsers.theNumberOfUsers())) {
            throw new IllegalStateException("이 강의의 최대 등록 가능 인원에 도달했습니다. 더 이상 사용자를 추가할 수 없습니다.");
        }

        this.baseRegisterUserMethod(user);
    }

    public void updateStateTo(SessionState state) {
        if (state == null) {
            throw new IllegalArgumentException("null로 상태를 업데이트할 수 없습니다.");
        }

        this.state = state;
    }


    public Long getId() {
        return this.id;
    }


    public Course getCourse() {
        return course;
    }

    public SessionState getState() {
        return state;
    }

    public SessionImage getCoverImage() {
        return coverImage;
    }

    public SessionDuration getDuration() {
        return duration;
    }

    public MaxRegister getMaxUserCount() {
        return maxUserCount;
    }

    public int getFee() {
        return fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", state=" + state +
                ", registeredUsers=" + registeredUsers +
                ", coverImage=" + coverImage +
                ", duration=" + duration +
                ", maxUserCount=" + maxUserCount +
                ", fee=" + fee +
                '}';
    }
}