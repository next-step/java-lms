package nextstep.courses.domain;

import nextstep.courses.exception.CannotRegisterException;
import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class SessionUsers {
    private final Set<NsUser> users;
    private final Capacity capacity;

    public SessionUsers(int capacity) {
        this.capacity = new Capacity(capacity);
        this.users = new HashSet<>();
    }


    public synchronized void updateCapacity(int value) {
        if (value < users.size()) {
            throw new IllegalStateException("변경하려는 수강 제한 인원이 현재 수강신청한 인원보다 작습니다");
        }
        capacity.update(value);
    }

    public synchronized void add(NsUser user) {
        if (users.size() >= capacity.value()) {
            throw new CannotRegisterException("강의 최대 수강 인원을 초과했습니다");
        }
        if (users.contains(user)) {
            throw new CannotRegisterException("이미 등록한 유저입니다");
        }
        users.add(user);
    }

    public int count() {
        return users.size();
    }
}
