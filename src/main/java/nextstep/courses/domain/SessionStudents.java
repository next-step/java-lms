package nextstep.courses.domain;

import nextstep.courses.exception.StudentAlreadyApplyException;

import java.util.Collections;
import java.util.Set;

public class SessionStudents {

    private final Set<SessionStudent> students;

    public SessionStudents(Set<SessionStudent> sessionStudents) {
        this.students = sessionStudents;
    }

    public void add(SessionStudent sessionStudent) {
        isAlreadyApplied(sessionStudent);
        this.students.add(sessionStudent);
    }

    private void isAlreadyApplied(SessionStudent sessionStudent) {
        if(this.students.contains(sessionStudent)) {
            throw new StudentAlreadyApplyException("이미 신청완료한 학생입니다.");
        }
    }

    public boolean isMaxParticipants(int count) {
        return students.size() >= count;
    }

    public Set<SessionStudent> getStudents() {
        return Collections.unmodifiableSet(this.students);
    }
}
