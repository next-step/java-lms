package nextstep.users.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NsStudent extends NsUser{

    private Map<Long, StudentStatus> sessionEnrollment;

    public NsStudent(Long id, String userId, String password, String name, String email){
        super(id, userId, password, name, email, LocalDateTime.now(), null);
        this.sessionEnrollment = new HashMap<>();
    }

    public void register(Long sessionId){
        sessionEnrollment.put(sessionId, StudentStatus.REQUEST);
    }

    public void approveToSession(Long sessionId){
        sessionEnrollment.put(sessionId, StudentStatus.APPROVE);
    }

    public void refusedToSession(Long sessionId){
        sessionEnrollment.put(sessionId, StudentStatus.REFUSE);
    }

    public Map<Long, StudentStatus> sessionEnrollment() {
        return sessionEnrollment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NsStudent nsStudent = (NsStudent) o;
        return Objects.equals(sessionEnrollment, nsStudent.sessionEnrollment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionEnrollment);
    }
}
