package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class SessionStudents {

    private final long sessionId;
    private final int fixedNumberOfStudent;
    private Set<Long> studentIds = new HashSet<>();

    public SessionStudents(long sessionId, int fixedNumberOfStudent) {
        this.sessionId = sessionId;
        this.fixedNumberOfStudent = fixedNumberOfStudent;
    }

    public SessionStudents(long sessionId, int fixedNumberOfStudent, Set<Long> studentIds) {
        this.sessionId = sessionId;
        this.fixedNumberOfStudent = fixedNumberOfStudent;
        this.studentIds = studentIds;
    }


    public int getNumberOfStudents() {
        return this.studentIds.size();
    }


}
