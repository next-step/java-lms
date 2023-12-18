package nextstep.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Participants {

    private final List<NsUser> students;

    public Participants() {
        this.students = new ArrayList<>();
    }

    public void addStudent(final NsUser student) {
        validateDuplicateStudent(student);
        students.add(student);
    }

    private void validateDuplicateStudent(NsUser student) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("이미 수강신청을 완료한 회원입니다.");
        }
    }

    public int count() {
        return students.size();
    }

    public List<NsUser> getStudents() {
        return Collections.unmodifiableList(students);
    }

}
