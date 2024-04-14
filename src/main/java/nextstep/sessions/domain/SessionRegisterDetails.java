package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionRegisterDetails {

    private int currentCountOfStudents;

    private final int maxOfStudents;

    private final long price;

    private final SessionType sessionType;

    private final SessionStatus sessionStatus;

    private List<NsUser> listeners;

    public SessionRegisterDetails(int currentCountOfStudents,
                                  int maxOfStudents,
                                  long price,
                                  SessionType sessionType,
                                  SessionStatus sessionStatus
    ) {
        this(currentCountOfStudents, maxOfStudents, price, sessionType, sessionStatus, new ArrayList<>());
    }

    public SessionRegisterDetails(int currentCountOfStudents,
                                  int maxOfStudents,
                                  long price,
                                  SessionType sessionType,
                                  SessionStatus sessionStatus,
                                  List<NsUser> listeners
    ) {
        this.currentCountOfStudents = currentCountOfStudents;
        this.maxOfStudents = maxOfStudents;
        this.price = price;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.listeners = listeners;
    }

    public void register(NsUser listener, Long amount) {
        if (this.sessionStatus.isNotRecruiting()) {
            throw new IllegalArgumentException(String.format("현재 강의는 (%s)인 상태입니다.", this.sessionStatus));
        }
        if (!this.sessionType.isCapacityExceeded(currentCountOfStudents, maxOfStudents)) {
            throw new IllegalArgumentException(String.format("이 강의의 현재 수강 신청 인원: (%s)명, 최대 수강 인원: (%s)명이므로 현재 마감이 된 상태입니다.", this.currentCountOfStudents, this.maxOfStudents));
        }
        if (this.isNotSamePrice(amount)) {
            throw new IllegalArgumentException("결제한 금액이 강의의 가격과 일치하지 않습니다.");
        }
        this.currentCountOfStudents++;
        listeners.add(listener);
    }

    public boolean isNotSamePrice(long amount) {
        return this.price != amount;
    }

    public boolean isContainsListener(NsUser listener) {
        return listeners.contains(listener);
    }

}
