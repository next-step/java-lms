package nextstep.sessions.domain;

import nextstep.common.Period;

public class Session {

    // id
    private long id;

    // 강의 이름
    private String name;

    // 날짜
    private Period date;

    // 이미지
    private SessionImage image;

    // 가격
    private SessionCharge charge;

    // 인원수
    private int studentCount;

    // 상태
    private SessionStatus status;

    public Session(String name, Period date, SessionImage image, SessionCharge charge, SessionStatus status) {
        this.id = 0l;
        this.name = name;
        this.date = date;
        this.image = image;
        this.charge = charge;
        this.studentCount = 0;
        this.status = status;
    }

    public Session(long id, String name, Period date, SessionImage image, SessionCharge charge, int studentCount, SessionStatus status) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.image = image;
        this.charge = charge;
        this.studentCount = studentCount;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Period getDate() {
        return date;
    }

    public SessionImage getImage() {
        return image;
    }

    public SessionCharge getCharge() {
        return charge;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public SessionStatus getStatus() {
        return status;
    }

    private boolean isInProgress() {
        return this.date.isInProgress();
    }

    public void checkSessionStatus() {
        if (!isInProgress()) {
            throw new IllegalStateException("진행중인 강의만 수강할 수 있습니다.");
        }
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 수강할 수 있습니다.");
        }
    }

    private void checkStudentCount() {
        int limitCount = this.charge.getLimitCount();
        if (limitCount > 0 && limitCount == this.studentCount) {
            throw new IllegalStateException("모집 인원이 마감되었습니다.");
        }
    }

    public void addStudent() {
        checkStudentCount();
        this.studentCount++;
    }


}
