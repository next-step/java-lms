package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentsException;
import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class Students {
    private final Set<NsUser> values;

    public Students() {
        this(new HashSet<>());
    }

    public Students(Set<NsUser> values) {
        this.values = values;
    }

    public void addStudent(NsUser nsUser) {
        validateDuplicate(nsUser);
        values.add(nsUser);
    }

    private void validateDuplicate(NsUser nsUser) {
        if (isContains(nsUser)) {
            throw new DuplicateStudentsException(nsUser.getUserId());
        }
    }

    public boolean isContains(NsUser nsUser) {
        return values.contains(nsUser);
    }

    public boolean isRegistrationFull(int maxStudents) {
        return values.size() == maxStudents;
    }

    @Override
    public String toString() {
        return "Students{" +
                "values=" + values +
                '}';
    }
}
