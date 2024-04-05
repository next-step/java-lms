package nextstep.courses.domain;

import nextstep.courses.domain.vo.Price;
import nextstep.users.domain.NsUser;

public class FreeEnrollCommand implements EnrollCommand {
    private NsUser nsUser;

    public FreeEnrollCommand(NsUser nsUser) {
        this.nsUser = nsUser;
    }

    @Override
    public NsUser userToEnroll(Price price) {
        return nsUser;
    }
}
