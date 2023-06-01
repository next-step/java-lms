package nextstep.courses.domain;

import nextstep.courses.exception.CannotRegisterException;
import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class SessionUsers {
    private final Set<NsUser> users;
    private final int capacity;

    public SessionUsers(int capacity) {
        this.users = new HashSet<>(capacity);
        this.capacity = capacity;
    }

    public void add(NsUser user) {
        if (users.size() >= capacity) {
            throw new CannotRegisterException("강의 최대 수강 인원을 초과했습니다");
        }
        if (users.contains(user)) {
            throw new CannotRegisterException("이미 등록한 유저입니다");
        }
    }
}
