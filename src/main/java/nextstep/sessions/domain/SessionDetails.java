package nextstep.sessions.domain;

public class SessionDetails {

    private int currentCountOfStudents;

    private final int maxOfStudents;

    private final long price;

    private final SessionType sessionType;

    private final SessionStatus sessionStatus;

    public SessionDetails(int currentCountOfStudents,
                          int maxOfStudents,
                          long price,
                          SessionType sessionType,
                          SessionStatus sessionStatus
    ) {
        this.currentCountOfStudents = currentCountOfStudents;
        this.maxOfStudents = maxOfStudents;
        this.price = price;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
    }

    public void register() {
        if (this.sessionStatus.isNotRecruiting()) {
            throw new IllegalArgumentException(String.format("현재 강의는 (%s)인 상태입니다.", this.sessionStatus));
        }
        if (!this.sessionType.isCapacityExceeded(currentCountOfStudents, maxOfStudents)) {
            throw new IllegalArgumentException(String.format("이 강의의 현재 수강 신청 인원: (%s)명, 최대 수강 인원: (%s)명이므로 현재 마감이 된 상태입니다.", this.currentCountOfStudents, this.maxOfStudents));
        }
        this.currentCountOfStudents++;
    }

    public boolean isNotSamePrice(long amount) {
        return this.price != amount;
    }

}
