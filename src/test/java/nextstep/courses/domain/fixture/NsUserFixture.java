package nextstep.courses.domain.fixture;

import nextstep.users.domain.NsUser;

import static nextstep.courses.domain.fixture.IdFixture.NS_USER_ID;

public class NsUserFixture {

    public static final String NS_USER_LOGIN_ID = "hellonayeon";
    public static final String NS_USER_PASSWORD = "****";
    public static final String NS_USER_NAME = "NaYeon Kwon";
    public static final String NS_USER_EMAIL = "hellonykwon@gmail.com";

    public static NsUser nsUser() {
        return new NsUser(NS_USER_ID, NS_USER_LOGIN_ID, NS_USER_PASSWORD, NS_USER_NAME, NS_USER_EMAIL);
    }

}
