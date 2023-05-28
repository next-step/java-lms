package nextstep.courses.domain;

import nextstep.courses.exception.SessionRegistrationException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionStudents {
    private List<NsUser> nsUsers = new ArrayList<>();
    private int maximumNumber;

    SessionStudents() {
    }

    public SessionStudents(int maximumNumber) {
        this.maximumNumber = maximumNumber;
    }

    public int countStudents() {
        return nsUsers.size();
    }

    public boolean addStudent(NsUser nsUser) {
        checkExceedLimit();
        return nsUsers.add(nsUser);
    }

    public void checkExceedLimit() {
        if (countStudents() >= maximumNumber) {
            throw new SessionRegistrationException("정원이 초과되었습니다.");
        }
    }

    public int getMaximumNumber() {
        return maximumNumber;
    }
}
