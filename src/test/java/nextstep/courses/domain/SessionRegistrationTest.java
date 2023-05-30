package nextstep.courses.domain;

import nextstep.courses.exception.ExceedingMaximumStudentException;
import nextstep.courses.exception.NotEligibleRegistrationStatusException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionRegistrationTest {

    private Session sessionMock;

    @BeforeEach
    void setUp() {
        sessionMock = SessionBuilder.aSession().build();
    }

    @Test
    @DisplayName("강의 최대 수강 인원을 초과할 수 없다.")
    void test01() {
        SessionRegistration registration = SessionRegistrationBuilder.aRegistration()
                .withStatus(SessionStatus.RECRUITING)
                .withStudents(new Students())
                .withStudentCapacity(1)
                .build();

        assertThatNoException().isThrownBy(() -> registration.register(NsUserTest.JAVAJIGI, sessionMock));
        assertThatThrownBy(() -> registration.register(NsUserTest.SANJIGI, sessionMock))
                .isInstanceOf(ExceedingMaximumStudentException.class);
    }

    @Test
    @DisplayName("수강신청은 강의 상태가 모집중일 때만 가능하다.")
    void test02() {
        SessionRegistrationBuilder emptyStatusRegistration
                = SessionRegistrationBuilder.aRegistration()
                .withStudents(new Students())
                .withStudentCapacity(1);

        SessionRegistration preparingRegistration
                = emptyStatusRegistration.but().withStatus(SessionStatus.PREPARING).build();
        SessionRegistration recruitingRegistration
                = emptyStatusRegistration.but().withStatus(SessionStatus.RECRUITING).build();
        SessionRegistration endedRegistration
                = emptyStatusRegistration.but().withStatus(SessionStatus.ENDED).build();

        assertThatNoException()
                .isThrownBy(() -> recruitingRegistration.register(NsUserTest.JAVAJIGI, sessionMock));
        assertThatThrownBy(() -> preparingRegistration.register(NsUserTest.SANJIGI, sessionMock))
                .isInstanceOf(NotEligibleRegistrationStatusException.class);
        assertThatThrownBy(() -> endedRegistration.register(NsUserTest.SANJIGI, sessionMock))
                .isInstanceOf(NotEligibleRegistrationStatusException.class);
    }

}
