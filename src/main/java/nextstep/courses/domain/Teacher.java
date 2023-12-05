package nextstep.courses.domain;

import nextstep.courses.domain.repository.SessionNsUser;

public class Teacher {

    private final Long id;
    private final String name;

    public Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void approve(SessionNsUser sessionNsUser) {
        sessionNsUser.approve();
    }

    public void reject(SessionNsUser sessionNsUser) {
        sessionNsUser.reject();
    }

}
