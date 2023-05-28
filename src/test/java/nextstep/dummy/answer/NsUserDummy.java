package nextstep.dummy.answer;

import nextstep.users.domain.NsUser;

public class NsUserDummy {

    public final NsUser a_user;

    public final NsUser b_user;

    public NsUserDummy() {
        this.a_user = new NsUser(1L, "a", "password", "b", "a@slipp.net");
        this.b_user = new NsUser(2L, "b", "password", "a", "b@slipp.net");
    }

}
