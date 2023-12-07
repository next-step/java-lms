package nextstep.courses.domain.session.student;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private List<NsUser> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public boolean add(NsUser student) {
        validateDuplicate(student);

        return this.students.add(student);
    }

    private void validateDuplicate(NsUser student) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("이미 해당 강의를 수강 중 입니다.");
        }
    }

    public int size() {
        return this.students.size();
    }
}
