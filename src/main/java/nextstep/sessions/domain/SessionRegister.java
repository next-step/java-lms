package nextstep.sessions.domain;

import nextstep.sessions.CannotRegisterException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionRegister {

    private final int maximumNumbers;
    private List<NsUser> users;

    public SessionRegister(int maximumNumbers) {
        this(maximumNumbers, new ArrayList<>());
    }

    public SessionRegister(int maximumNumbers, List<NsUser> users) {
        this.maximumNumbers = maximumNumbers;
        this.users = users;
    }

    public List<NsUser> getUsers() {
        return users;
    }

    public void register(NsUser user) {
        if (users.size() >= maximumNumbers) {
            throw new CannotRegisterException("강의는 최대 수강 인원을 초과할 수 없습니다. 최대 인원: " + maximumNumbers);
        }

        users.add(user);
    }

    public int count() {
        return users.size();
    }

}
