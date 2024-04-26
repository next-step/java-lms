package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Students {

    private final List<NsUser> students = new ArrayList<>();

    public void admit(NsUser student) {
        validate(student);
        this.students.add(student);
    }
    public int size() {
        return students.size();
    }

    private void validate(NsUser student) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("이미 등록한 학생입니다.");
        }
    }
}
