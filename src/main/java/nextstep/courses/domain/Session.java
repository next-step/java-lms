package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Session {

    private Long id;
    private String title;
    private int generation;
    private DateTime dateTime;
    private SessionType type;
    private Register register;

    public Session() {
    }

    public Session(Long id, String title, int generation, DateTime dateTime, SessionType type, Register register) {
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.dateTime = dateTime;
        this.type = type;
        this.register = register;
    }

    public NsUsers register(NsUser loginUser) {
        return register.add(loginUser);
    }
}
