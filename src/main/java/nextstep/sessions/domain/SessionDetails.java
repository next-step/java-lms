package nextstep.sessions.domain;

public class SessionDetails {

    private int countOfStudents;

    private final int maxOfStudents;

    private final int price;

    private final SessionType sessionType;

    private final SessionStatus sessionStatus;

    // 강의디테일: 현재 수강인원, 최대 수강인원, SessionType, SessionStatus
    public SessionDetails(int countOfStudents, int maxOfStudents, int price, SessionType sessionType, SessionStatus sessionStatus) {
        this.countOfStudents = countOfStudents;
        this.maxOfStudents = maxOfStudents;
        this.price = price;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
    }

    public void register() {
        if (this.countOfStudents + 1 > maxOfStudents) {
            throw new IllegalArgumentException();
        }
        this.countOfStudents++;
    }

}
