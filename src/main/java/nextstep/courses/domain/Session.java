package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public abstract class Session {

    protected final int maximumNumberOfStudent;
    protected final List<NsUser> students = new ArrayList<>();

    protected Session(int maximumNumberOfStudent) {
        this.maximumNumberOfStudent = maximumNumberOfStudent;
    }

    public abstract void register(List<NsUser> users);
}
