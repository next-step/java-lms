package nextstep.courses.domain;

import nextstep.courses.AlreadyJoinStudentException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private final List<NsUser> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public Students(List<NsUser> students) {
        this.students = students;
    }

    public void add(NsUser student) {
        validate(student);

        this.students.add(student);
    }

    public int totalCount() {
        return this.students.size();
    }

    private void validate(NsUser student) {
        if (isAlreadyStudent(student)) {
            throw new AlreadyJoinStudentException("이미 수강신청을 완료한 학생입니다.");
        }
    }

    public boolean isAlreadyStudent(NsUser student) {
        return this.students.contains(student);
    }
}
