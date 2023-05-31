package nextstep.sessions.domain;

import nextstep.sessions.ExceedingMaximumStudentException;
import nextstep.sessions.NotEligibleRegistrationStatusException;
import nextstep.students.domain.Students;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionRegistrationTest {

    private Session sessionMock;
    private SessionRegistrationBuilder emptyStatusRegistration;

    @BeforeEach
    void setUp() {
        sessionMock = SessionBuilder.aSession().build();
        emptyStatusRegistration = SessionRegistrationBuilder.aRegistration()
                .withStudents(new Students())
                .withStudentCapacity(2);
    }

    @Test
    @DisplayName("강의 최대 수강 인원을 초과할 수 없다.")
    void test01() {
        SessionRegistration registration = emptyStatusRegistration.but()
                .withStatus(SessionStatus.PREPARING)
                .withRecruitmentStatus(SessionRecruitmentStatus.RECRUITING)
                .withStudentCapacity(1)
                .build();

        assertThatNoException().isThrownBy(() -> registration.register(NsUserTest.JAVAJIGI, sessionMock));
        assertThatThrownBy(() -> registration.register(NsUserTest.SANJIGI, sessionMock))
                .isInstanceOf(ExceedingMaximumStudentException.class);
    }

    private static Stream<Arguments> provideArgumentsForRegistrableStatusTest() {
        return Stream.of(
                Arguments.of(SessionStatus.PREPARING, SessionRecruitmentStatus.RECRUITING),
                Arguments.of(SessionStatus.PROGRESSING, SessionRecruitmentStatus.RECRUITING)
        );
    }

    @DisplayName("강의진행상태가 준비중,진행중 이면서 모집상태가 모집인 경우 수강신청이 가능해야 한다.")
    @ParameterizedTest(name = "수강신청가능: 강의진행상태-{0} & 강의모집상태-{1}")
    @MethodSource("provideArgumentsForRegistrableStatusTest")
    void test02(SessionStatus status, SessionRecruitmentStatus recruitmentStatus) {
        SessionRegistration registration
                = emptyStatusRegistration.but()
                .withStatus(status)
                .withRecruitmentStatus(recruitmentStatus)
                .build();

        assertThatNoException()
                .isThrownBy(() -> registration.register(NsUserTest.SANJIGI, sessionMock));
    }

    private static Stream<Arguments> provideArgumentsForNotRegistrableStatusTest() {
        return Stream.of(
                Arguments.of(SessionStatus.ENDED, SessionRecruitmentStatus.RECRUITING),
                Arguments.of(SessionStatus.PREPARING, SessionRecruitmentStatus.NOT_RECRUITING),
                Arguments.of(SessionStatus.PROGRESSING, SessionRecruitmentStatus.NOT_RECRUITING),
                Arguments.of(SessionStatus.ENDED, SessionRecruitmentStatus.NOT_RECRUITING)
        );
    }

    @DisplayName("강의진행상태가 종료 이거나 모집상태가 비모집인 경우 수강신청이 불가능해야 한다.")
    @ParameterizedTest(name = "수강신청불가: 강의진행상태-{0} & 강의모집상태-{1}")
    @MethodSource("provideArgumentsForNotRegistrableStatusTest")
    void test03(SessionStatus status, SessionRecruitmentStatus recruitmentStatus) {
        SessionRegistration registration
                = emptyStatusRegistration.but()
                .withStatus(status)
                .withRecruitmentStatus(recruitmentStatus)
                .build();

        assertThatThrownBy(() -> registration.register(NsUserTest.SANJIGI, sessionMock))
                .isInstanceOf(NotEligibleRegistrationStatusException.class);
    }

}
