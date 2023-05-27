package nextstep.courses.domain;

import java.util.ArrayList;

public class Session {
    private final Course course;
    private final Students students;
    private final int capacity;

    public Session(Course course, int capacity) {
        this.course = course;
        this.students = new Students(this, new ArrayList<>());
        this.capacity = capacity;
    }

    public void register(Student student) {
        validateToRegister();

        students.add(student);
    }

    public int getStudentsSize() {
        return students.size();
    }

    private void validateToRegister() {
        if (course.isNotRecruiting()) {
            throw new IllegalStateException("아직 모집중인 강의가 아닙니다.");
        }

        if (capacity == students.size()) {
            throw new IllegalStateException("더 이상 학생을 등록할 수 없습니다.");
        }
    }
}
