package nextstep.sessions.domain;

public class SessionDetails {

    private int currentCountOfStudents;

    private final int maxOfStudents;

    private final int price;

    private final SessionType sessionType;

    private final SessionStatus sessionStatus;

    // 강의디테일: 현재 수강인원, 최대 수강인원, SessionType, SessionStatus
    public SessionDetails(int currentCountOfStudents, int maxOfStudents, int price, SessionType sessionType, SessionStatus sessionStatus) {
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
        //todo: 아래 로직은 유료강의 일때만 해당된다
        if (this.currentCountOfStudents + 1 > maxOfStudents) {
            throw new IllegalArgumentException();
        }
        this.currentCountOfStudents++;
    }

}
