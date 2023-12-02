package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.courses.exception.CanNotRegisterSessionException;
import nextstep.courses.exception.ExceedStudentsCountException;
import nextstep.courses.exception.PaymentMisMatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Session extends BaseEntity {

    private static final Integer FREE_PRICE = 0;

    private final Long id;
    private final String title;
    private final SessionImages sessionImages;
    private final Students students;
    private final SessionType sessionType;
    private final Integer maxParticipants;
    private final Integer price;
    private final SessionStatus status;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public Session(Long id, String title, SessionImages sessionImages, SessionType sessionType, Integer maxParticipants, Integer price, SessionStatus status, LocalDateTime startAt, LocalDateTime endAt) {
        super();
        validate(price, maxParticipants, startAt, endAt);
        this.id = id;
        this.title = title;
        this.sessionImages = sessionImages;
        this.sessionType = sessionType;
        this.maxParticipants = maxParticipants;
        this.price = price;
        this.students = new Students(new HashSet<>());
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static Session ofFree(Long id, String title, SessionImages sessionImages, Integer maxParticipants, SessionStatus sessionStatus, LocalDateTime startAt, LocalDateTime endAt) {
        return new Session(id, title, sessionImages, SessionType.FOR_FREE, maxParticipants, FREE_PRICE, sessionStatus, startAt, endAt);
    }

    public static Session ofCostMoney(Long id, String title, SessionImages sessionImages, Integer maxParticipants, SessionStatus sessionStatus, Integer price, LocalDateTime startAt, LocalDateTime endAt) {
        return new Session(id, title, sessionImages, SessionType.COST_MONEY, maxParticipants, price, sessionStatus, startAt, endAt);
    }

    public Set<NsUser> getStudents() {
        return this.students.getStudents();
    }

    public void registerCostMoneySession(NsUser nsUser, Payment payment) {
        validateRegisterCostMoney(payment);
        students.add(nsUser);
    }

    public void registerFreeSession(NsUser nsUser) {
        isSessionRegistering();
        students.add(nsUser);
    }

    private void validate(Integer price, Integer maxParticipants, LocalDateTime startAt, LocalDateTime endAt) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }

        if (maxParticipants < 0) {
            throw new IllegalArgumentException("정원은 음수일 수 없습니다.");
        }

        if (startAt == null) {
            throw new IllegalArgumentException("강의 시작일이 없습니다.");
        }

        if (endAt == null) {
            throw new IllegalArgumentException("강의 종료일이 없습니다.");
        }
    }

    private void validateRegisterCostMoney(Payment payment) {
        isSessionRegistering();

        if (this.sessionType.equals(SessionType.COST_MONEY) && students.isMaxParticipants(this.maxParticipants)) {
            throw new ExceedStudentsCountException("정원이 초과되어 더 이상 신청할 수 없습니다.");
        }

        if (!payment.isPaidPriceSame(this.price)) {
            throw new PaymentMisMatchException("지불한 금액이 강의 가격과 일치하지 않습니다.");
        }
    }

    private void isSessionRegistering() {
        if (SessionStatus.READY.equals(this.status)) {
            throw new CanNotRegisterSessionException("강의가 모집중이여야만 신청할 수 있습니다.");
        }
    }
}
