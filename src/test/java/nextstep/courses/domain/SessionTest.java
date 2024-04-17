package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.courses.domain.utils.TestSessionFactory;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private final Long sessionId = 1L;
    private final NsUser student = NsUserTest.JAVAJIGI;

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RECRUIT"})
    @DisplayName("모집 중이 아닌 강의에 수강신청 하는 경우 에러 발생")
    void testInvalidEnrollmentUser(RecruitStatus recruitStatus) {
        Session testSession = TestSessionFactory.makeSession(sessionId, recruitStatus);

        assertThatThrownBy(() -> testSession.enrollmentUser(student, new Payment())).isInstanceOf(NotRecruitException.class);
    }


    @Test
    @DisplayName("수강신청한 사람이 한 번 더 수강신청할 경우 에러 발생")
    void testDuplicateEnrollment() {
        Session testSession = TestSessionFactory.recruitStatusSession(sessionId);
        testSession.enrollmentUser(student, new Payment());

        assertThatThrownBy(() -> testSession.enrollmentUser(student, new Payment())).isInstanceOf(NotRecruitException.class);
    }

    @Test
    @DisplayName("1장 이상의 이미지가 들어갈 수 있는지 테스트")
    void testManyImage() {
        List<SessionImage> sessionImages = List.of(SessionImageTest.S1, SessionImageTest.S2);
        Session testSession = TestSessionFactory.recruitSession(sessionId, sessionImages);

        assertThat(testSession.getSessionImage()).hasSize(2)
                .containsExactlyInAnyOrderElementsOf(sessionImages);
    }

    @Test
    @DisplayName("진행 중인 강의 수강신청")
    void testEnrollmentWhenSessionRun() {
        Session testSession = TestSessionFactory.recruitStatusSession(sessionId);
        testSession.changeProgressStatus(SessionProgressStatus.RUN);
        testSession.enrollmentUser(student, new Payment());

        assertThat(testSession.getStudents()).hasSize(1);
    }
}
