package nextstep.courses.domain;

public class SessionInformationTest {
    public static final SessionInformation TDD_SESSION_INFORMATION = new SessionInformation(
            SessionCoverImageTest.TDD_SESSION_COVER_IMAGE, SessionName.of("TDD"), SessionDateTest.TDD_SESSION_DATE
    );
}
