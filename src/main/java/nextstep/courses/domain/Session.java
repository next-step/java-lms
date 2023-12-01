package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private SessionStatus status;
    private List<NsUser> registeredUser = new ArrayList<>();

    public Session() {
    }

    public Session(SessionStatus status) {
        this.status = status;
    }

    public List<NsUser> register(NsUser user) throws CannotRegisterException {
        if (!SessionStatus.OPEN.equals(status)) {
            throw new CannotRegisterException("모집중이 아닌 경우 신청이 불가합니다");
        }
        registeredUser.add(user);
        return registeredUser;
    }
}
