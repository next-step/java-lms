package nextstep.fixture;

import nextstep.users.domain.NsUser;

public class NsUserFixture {

    private static final String TEST_PASSWORD = "password";
    private static final String TEST_NAME = "name";
    private static final String JAVAJIGI_NAME = "javajigi";
    private static final String JAVAJIGI_SLIPP_NET = "javajigi@slipp.net";
    private static final String SANJIGI1 = "sanjigi";
    private static final String SANJIGI_SLIPP_NET = "sanjigi@slipp.net";

    public static final NsUser JAVAJIGI = new NsUser(1L,
                                                     JAVAJIGI_NAME,
                                                     TEST_PASSWORD,
                                                     TEST_NAME,
                                                     JAVAJIGI_SLIPP_NET);
    public static final NsUser SANJIGI = new NsUser(2L,
                                                    SANJIGI1,
                                                    TEST_PASSWORD,
                                                    TEST_NAME,
                                                    SANJIGI_SLIPP_NET);
}
