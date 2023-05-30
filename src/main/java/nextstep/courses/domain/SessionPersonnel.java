package nextstep.courses.domain;

import java.util.concurrent.CopyOnWriteArrayList;
import nextstep.users.domain.NsUser;

public class SessionPersonnel {

    private final int capacity;

    private final CopyOnWriteArrayList<NsUser> users;

    public SessionPersonnel(int capacity) {
        this(capacity, new CopyOnWriteArrayList<>());
    }

    public SessionPersonnel(int capacity, CopyOnWriteArrayList<NsUser> users) {
        this.capacity = capacity;
        this.users = users;
    }

    public void register(NsUser user) {
        validRegister(user);
        users.add(user);
    }

    private void validRegister(NsUser user) {
        if (capacity <= users.size()) {
            throw new RuntimeException("최대 인원을 초과할 수 없습니다.");
        }
        if (users.contains(user)) {
            throw new RuntimeException("중복 유저가 존재합니다.");
        }
        if (user.isGuestUser()) {
            throw new RuntimeException("Guest는 신청을 할 수 없습니다.");
        }
    }

}
