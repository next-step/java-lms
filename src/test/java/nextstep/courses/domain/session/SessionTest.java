package nextstep.courses.domain.session;

import nextstep.courses.exceptions.AlreadyEnrollmentException;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nextstep.courses.domain.fixture.EnrollmentFixture.강의_진행중;
import static nextstep.users.domain.NsUserFixture.HYUNGKI;
import static nextstep.users.domain.NsUserFixture.JAVAJIGI;
import static nextstep.users.domain.NsUserFixture.SANJIGI;

class SessionTest {

    private List<NsUser> students = new ArrayList<>(List.of(SANJIGI, HYUNGKI));

    private final Session session = Session.builder()
            .enrollment(강의_진행중)
            .build();

    @Test
    @DisplayName("강의 개설 테스트")
    void sessionSuccessTest() throws AlreadyEnrollmentException {
        // when & then
        session.enroll(JAVAJIGI, students);
    }

    @Test
    @DisplayName("강의 개설 실패 테스트 (이미 수강신청한 학생의 경우)")
    void sessionFailTest() {
        // when & then
        Assertions.assertThatThrownBy(() -> session.enroll(SANJIGI, students))
                .isInstanceOf(AlreadyEnrollmentException.class);
    }

}