package nextstep.dummy.answer;

import nextstep.users.domain.NsUser;

public class NsUserDummy {

    public final NsUser javajigi;

    public final NsUser sanjigi;


    public NsUserDummy() {
        this.javajigi = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        this.sanjigi = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    }

}
