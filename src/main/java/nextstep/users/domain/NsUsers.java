package nextstep.users.domain;

import java.util.HashSet;
import java.util.Set;

public class NsUsers {

    private final Set<NsUser> students;

    public NsUsers(Set<NsUser> students) {
        this.students = students;
    }

    public NsUsers() {
        this(new HashSet<>());
    }

    public void register(NsUser nsUser) {
        students.add(nsUser);
    }

    public int size() {
        return students.size();
    }
}
