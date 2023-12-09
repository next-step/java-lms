package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicantsTest {
    private static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    private static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    private static final NsUser APPLE = new NsUser(3L, "sanjigi", "password", "name", "apple@slipp.net");

    private Applicants applicants;
    private SessionState sessionState;

    @BeforeEach
    void setUp() {
        applicants = new Applicants();
        sessionState = new SessionState(SessionType.FREE, 1000L, 10);
        applicants.addApplicant(JAVAJIGI, sessionState);
    }

    @Test
    @DisplayName("addApplicant 는 이미 수강생이 강의를 신청 했다면 예외를 던진다.")
    void addApplicant_alreadyExistedApplicant_throwsException() {
        assertThatThrownBy(
                () -> applicants.addApplicant(JAVAJIGI, sessionState)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("addApplicant 는 유료강의 수강 정원이 찼으면 예외를 던진다.")
    void addApplicant_alreadyFull_throwsException() {
        applicants = new Applicants();
        sessionState = new SessionState(SessionType.CHARGE, 1000L, 2);
        applicants.addApplicant(SANJIGI, sessionState);
        applicants.addApplicant(APPLE, sessionState);

        assertThatThrownBy(
                () -> applicants.addApplicant(SANJIGI, sessionState)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
