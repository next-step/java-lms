package nextstep.session.domain;

import nextstep.session.StudentNumberExceededException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private Long maxNumberOfStudent;
    private final List<NsUser> students = new ArrayList<>();

    public Student(Long maxNumberOfStudent) {
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public Long getMaxNumberOfStudent() {
        return maxNumberOfStudent;
    }

    public List<NsUser> getStudents() {
        return students;
    }

    public void signUp(NsUser user) throws StudentNumberExceededException {
        if (students.size() == maxNumberOfStudent) {
            throw new StudentNumberExceededException("최대 수강 인원 수를 초과했습니다.");
        }

        this.students.add(user);
    }
}
