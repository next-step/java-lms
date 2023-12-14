package nextstep.sessions.domain;

import nextstep.common.Period;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    // id
    private Long id;

    // 강의 이름
    private String name;

    // 날짜
    private Period date;

    // 이미지
    private SessionImage image;

    // 가격
    private SessionCharge charge;

    // 상태
    private SessionStatus status;

    // 수강신청자 목록
    private SessionStudents students;

    public Session(String name, Period date, SessionImage image, SessionCharge charge, SessionStatus status) {
        this.id = 0l;
        this.name = name;
        this.date = date;
        this.image = image;
        this.charge = charge;
        this.status = status;
        this.students = new SessionStudents();
    }

    public Session(Long id, String name, Period date, SessionImage image, SessionCharge charge, SessionStatus status, SessionStudents students) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.image = image;
        this.charge = charge;
        this.status = status;
        this.students = students;
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

    public SessionStatus getStatus() {
        return status;
    }

    public SessionStudents getStudents() {
        return students;
    }

    public int getStudentCount() {
        return this.students.size();
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

    public SessionStudent enroll(NsUser user) {
        if (user.isGuestUser()) {
            throw new IllegalStateException("로그인 후 신청할 수 있습니다.");
        }
        checkSessionStatus();
        this.charge.checkRecruits(students.size());
        SessionStudent newStudent = new SessionStudent(user, LocalDateTime.now());
        students.addStudent(newStudent);
        return newStudent;
    }
}
