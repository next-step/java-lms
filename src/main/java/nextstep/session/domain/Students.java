package nextstep.session.domain;

import nextstep.exception.StudentsException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Students {

    private final List<NsUser> students;

    public Students(List<NsUser> students) {
        this.students = students;
    }

    public Students() {
        students = new ArrayList<>();
    }


    public void add(NsUser nsUser) {
        validateDuplicate(nsUser);
        students.add(nsUser);
    }

    private void validateDuplicate(NsUser nsUser) {
        boolean hasStudent = hasStudent(nsUser);
        if (hasStudent) {
            throw new StudentsException("이미 등록된 회원입니다.");
        }
    }

    private boolean hasStudent(NsUser nsUser) {
        return this.students.stream()
                .anyMatch(student -> student.matchUser(nsUser));
    }

    public Optional<NsUser> findStudent(NsUser nsUser) {
        return this.students.stream()
                .filter(student -> student.matchUser(nsUser))
                .findAny();
    }

    public void remove(NsUser nsUser) {
        boolean hasStudent = hasStudent(nsUser);
        if (!hasStudent) {
            throw new StudentsException("등록된 회원이 아닙니다.");
        }

        this.students.remove(nsUser);
    }
}
