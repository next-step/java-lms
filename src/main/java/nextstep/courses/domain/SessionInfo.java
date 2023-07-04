package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class SessionInfo {
    private final LectureStatus lectureStatus;
    private final List<Student> users;
    private final int maxUser;

    public SessionInfo(LectureStatus lectureStatus, List<Student> users, int maxUser) {
        this.lectureStatus = lectureStatus;
        this.users = users;
        this.maxUser = maxUser;
    }

    public void register(Student student) {
        if (!this.lectureStatus.isRecruiting()) {
            throw new RuntimeException("수강신청은 모집중일때 가능합니다.");
        }

        if (this.users.size() >= maxUser) {
            throw new RuntimeException("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
        }

        users.add(student);
    }


    public LectureStatus getLectureStatus() {
        return lectureStatus;
    }

    public List<Student> getUsers() {
        return users;
    }

    public int getMaxUser() {
        return maxUser;
    }
}
