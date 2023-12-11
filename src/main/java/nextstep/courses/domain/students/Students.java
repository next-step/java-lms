package nextstep.courses.domain.students;

import nextstep.courses.CanNotEnrollSameStudentsException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private List<NsUser> students = new ArrayList<>();

    public Students() {}

    public Students(List<NsUser> students) {
        this.students = students;
    }

    public int size() {
        return students.size();
    }

    public void add(NsUser nsUser) {
        if (students.contains(nsUser)) {
            throw new CanNotEnrollSameStudentsException("동일한 학생이 2번 수강신청할 수 없습니다.");
        }
        students.add(nsUser);
    }
}
