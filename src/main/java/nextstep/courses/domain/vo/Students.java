package nextstep.courses.domain.vo;

import nextstep.courses.exception.AlreadyEnrolledException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Students {

    private List<NsUser> studentList;

    public Students() {
        this(new ArrayList<>());
    }

    public Students(List<NsUser> studentList) {
        this.studentList = studentList;
    }

    public List<NsUser> students() {
        return Collections.unmodifiableList(studentList);
    }

    public void add(NsUser student) throws AlreadyEnrolledException {
        if (studentList.contains(student)) {
            throw new AlreadyEnrolledException("이미 수강신청한 학생입니다.");
        }
        studentList.add(student);
    }

    public int count() {
        return studentList.size();
    }
}
