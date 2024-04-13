package nextstep.courses.domain.fixture;

import nextstep.users.domain.NsUser;

import static nextstep.courses.domain.fixture.IdFixture.NS_USER_ID;

public class NsUserFixture {

    private static final String NS_USER_LOGIN_ID = "hellonayeon";
    private static final String NS_USER_PASSWORD = "****";
    private static final String NS_USER_NAME = "나연";
    private static final String NS_USER_EMAIL = "hellonykwon@gmail.com";

    public static NsUser nsUser() {
        return new NsUser(NS_USER_ID, NS_USER_LOGIN_ID, NS_USER_PASSWORD, NS_USER_NAME, NS_USER_EMAIL);
    }

}
