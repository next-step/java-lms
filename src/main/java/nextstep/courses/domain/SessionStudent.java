package nextstep.courses.domain;

import nextstep.users.domain.NsStudent;

import java.util.HashMap;
import java.util.Map;

public class SessionStudent {

    private Map<String, NsStudent> nsStudents;

    public SessionStudent() {
        this.nsStudents = new HashMap<>();
    }

    public void register(NsStudent nsStudent, Long sessionId) {
        nsStudents.put(nsStudent.getEmail(), nsStudent);
        nsStudent.register(sessionId);
    }

    public void approve(NsStudent nsStudent, Long sessionId) {
        NsStudent student = nsStudents.get(nsStudent.getEmail());
        student.approveToSession(sessionId);
        nsStudents.put(nsStudent.getEmail(), student);
    }

    public void refuse(NsStudent nsStudent, Long sessionId) {
        NsStudent student = nsStudents.get(nsStudent.getEmail());
        student.refusedToSession(sessionId);
        nsStudents.remove(nsStudent.getEmail());
    }

    public boolean hasStudent(NsStudent student) {
        return nsStudents.containsValue(student);
    }
}
