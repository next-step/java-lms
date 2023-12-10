package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.exception.CannotEnrollException;
import nextstep.users.domain.NsUser;

public class Session {

    private Type type;
    private int maxCountOfStudents = Integer.MAX_VALUE;
    private List<NsUser> students;

    public Session(Type type, int maxCountOfStudents) {
        this.students = new ArrayList<>();
        this.type = type;
        this.maxCountOfStudents = maxCountOfStudents;
    }

    public void enrollStudent(NsUser user) throws CannotEnrollException {
        if (type.equals(Type.PAID) && students.size() >= maxCountOfStudents) {
            throw new CannotEnrollException("유료 강의는 강의 최대 수강 인원을 초과할 수 없다");
        }
        if (this.students.contains(user)) {
            throw new CannotEnrollException("강의는 중복 수강신청할 수 없다");
        }
        this.students.add(user);
    }
}
