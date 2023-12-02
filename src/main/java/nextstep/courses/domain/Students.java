package nextstep.courses.domain;

import nextstep.courses.exception.StudentAlreadyApplyException;
import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.Set;

public class Students {

    private final Set<NsUser> students;

    public Students(Set<NsUser> nsUsers) {
        this.students = nsUsers;
    }

    public void add(NsUser user) {
        isAlreadyApplied(user);
        this.students.add(user);
    }

    private void isAlreadyApplied(NsUser user) {
        if(this.students.contains(user)) {
            throw new StudentAlreadyApplyException("이미 신청완료한 학생입니다.");
        }
    }

    public boolean isMaxParticipants(int count) {
        return students.size() >= count;
    }

    public Set<NsUser> getStudents() {
        return Collections.unmodifiableSet(this.students);
    }
}
