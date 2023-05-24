package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class Session {

    private final Set<Student> students = new HashSet<>();
    private Long maxNumOfStudent;

    public Session() {

    }

    public void add(Student student) {
        if (this.maxNumOfStudent <= this.students.size()) {
            throw new CannotEnrollException("강의 수강 신청 인원이 다 찼습니다!");
        }
        students.add(student);
    }

    public int totalStudentNum() {
        return students.size();
    }

    public void setMaxNumOfStudent(Long maxNumOfStudent) {
        this.maxNumOfStudent = maxNumOfStudent;
    }

    public Long getMaxNumOfStudent() {
        return maxNumOfStudent;
    }
}
