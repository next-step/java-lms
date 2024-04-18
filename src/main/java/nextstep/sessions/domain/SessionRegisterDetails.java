package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class SessionRegisterDetails {
    private final long id;
    private final CountOfStudent countOfStudent;

    private final Price price;

    private final SessionStatus sessionStatus;

    private List<NsUser> listeners;

    public SessionRegisterDetails(
            int currentCountOfStudents,
            int maxOfStudents,
            long price,
            SessionType sessionType,
            SessionStatus sessionStatus
    ) {
        this(currentCountOfStudents, maxOfStudents, price, sessionType, sessionStatus, new ArrayList<>());
    }

    public SessionRegisterDetails(
            int currentCountOfStudents,
            int maxOfStudents,
            long price,
            SessionType sessionType,
            SessionStatus sessionStatus,
            List<NsUser> listeners
    ) {
        this(new CountOfStudent(currentCountOfStudents, maxOfStudents, sessionType), new Price(price), sessionStatus, listeners);
    }

    public SessionRegisterDetails(
            CountOfStudent countOfStudent,
            Price price,
            SessionStatus sessionStatus,
            List<NsUser> listeners
    ) {
        this(0, countOfStudent, price, sessionStatus, listeners);
    }

    public SessionRegisterDetails(
            long id,
            CountOfStudent countOfStudent,
            Price price,
            SessionStatus sessionStatus,
            List<NsUser> listeners
    ) {
        this.id = id;
        this.countOfStudent = countOfStudent;
        this.price = price;
        this.sessionStatus = sessionStatus;
        this.listeners = listeners;
    }

    public void register(NsUser listener, Payment payment) {
        if (this.sessionStatus.isNotRecruiting()) {
            throw new IllegalArgumentException(String.format("현재 강의는 (%s)인 상태입니다.", this.sessionStatus));
        }
        if (this.price.isNotSamePrice(payment)) {
            throw new IllegalArgumentException("결제한 금액이 강의의 가격과 일치하지 않습니다.");
        }
        this.countOfStudent.increaseCountOfStudents();
        listeners.add(listener);
    }

    public boolean isNotSamePrice(long amount) {
        return price.isNotSamePrice(amount);
    }

    public boolean isContainsListener(NsUser listener) {
        return listeners.contains(listener);
    }

    public int getMaxCountOfStudents() {
        return countOfStudent.getMaxOfStudents();
    }

    public String getSessionType() {
        return countOfStudent.getSessionType();
    }

    public long getPrice() {
        return price.getPrice();
    }

    public String getSessionStatus() {
        return sessionStatus.name();
    }

    public long getId() {
        return id;
    }

    public List<NsUser> getListeners() {
        return listeners;
    }
}
