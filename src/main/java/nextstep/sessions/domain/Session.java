package nextstep.sessions.domain;

public class Session {

    // id
    private long id;

    // 강의 이름
    private String name;

    // 날짜
    private SessionDate date;

    // 이미지
    private SessionImage image;

    // 가격
    private SessionCharge charge;

    // 인원수
    private SessionStudent student;

    // 상태
    private SessionStatus status;

    public Session(String name, SessionDate date, SessionImage image, SessionCharge charge, SessionStudent student, SessionStatus status) {
        this.id = 0l;
        this.name = name;
        this.date = date;
        this.image = image;
        this.charge = charge;
        this.student = student;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SessionDate getDate() {
        return date;
    }

    public SessionCharge getCharge() {
        return charge;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public boolean isInProgress() {
        return this.date.isInProgress();
    }

    public void checkSessionStatus() {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 수강할 수 있습니다.");
        }
    }

    public void addStudent() {
        checkSessionStatus();
        this.student.addStudent();
    }


}
