package nextstep.courses.domain.session.enrollment;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private int maxEnrollmentCount;
    private final List<NsUser> studentList = new ArrayList<>();

    public StudentManager(final int maxEnrollmentCount) {
        this.maxEnrollmentCount = maxEnrollmentCount;
    }

    public void add(NsUser user) {
        studentList.add(user);
    }

    public boolean isMaxStudent() {
        return maxEnrollmentCount == studentList.size();
    }

    public int getStudentCount() {
        return studentList.size();
    }
}
