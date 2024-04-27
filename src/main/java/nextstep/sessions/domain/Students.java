package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final int maxOfStudents;
    private final SessionType sessionType;
    private final List<Student> students;

    public Students(int maxOfStudents) {
        this(maxOfStudents, SessionType.PAID, new ArrayList<>());
    }

    public Students(int maxOfStudents, SessionType sessionType, List<Student> students) {
        this.maxOfStudents = maxOfStudents;
        this.sessionType = sessionType;
        this.students = students;
    }

    public int countOfStudents() {
        return students.size();
    }

    public void enroll(Student student) {
        if (sessionType.isCapacityExceeded(countOfStudents(), maxOfStudents)) {
            throw new IllegalArgumentException(String.format("이 강의의 현재 수강 신청 인원: (%s)명, 최대 수강 인원: (%s)명이므로 현재 마감이 된 상태입니다.", countOfStudents(), maxOfStudents));
        }
        students.add(student);
    }
}
