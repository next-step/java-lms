package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private static final int MAX_STUDENTS = 3;
    private final List<NsUser> students = new ArrayList<>();

    public boolean hasStudent(NsUser student) {
        return students.contains(student);
    }

    public void add(NsUser loginUser) {
        if (students.size() >= MAX_STUDENTS) {
            throw new RuntimeException("수강 신청 인원이 초과되었습니다.");
        }
        students.add(loginUser);
    }
}
