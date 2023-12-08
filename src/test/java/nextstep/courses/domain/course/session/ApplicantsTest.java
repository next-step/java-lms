package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicantsTest {
    private static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    private static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    Applicants applicants;

    @BeforeEach
    void setUp() {
        applicants = new Applicants();
        applicants.add(JAVAJIGI);
        applicants.add(SANJIGI);
    }

    @Test
    @DisplayName("add 는 이미 수강생이 강의를 신청하였으면 예외를 던진다.")
    void add_alreadyExistedApplicant_throwsException() {
        assertThatThrownBy(
                () -> applicants.add(JAVAJIGI)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
