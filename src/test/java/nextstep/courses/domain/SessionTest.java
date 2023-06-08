package nextstep.courses.domain;

import nextstep.courses.domain.builder.EnrollmentBuilder;
import nextstep.courses.domain.builder.SessionBuilder;
import nextstep.courses.domain.fixture.SessionFixtures;
import nextstep.courses.exception.CannotRegisterException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SessionTest {
    private static final SessionPeriod PERIOD = new SessionPeriod(
            LocalDate.of(2023, 5, 23),
            LocalDate.of(2023, 5, 30)
    );


    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"RECRUITING"}, mode = EnumSource.Mode.EXCLUDE)
    public void 현재_모집중인_강의만_수강할_수_있다(SessionStatus status) throws Exception {
        Session session = new SessionBuilder()
                .withEnrollment(
                        new EnrollmentBuilder().withStatus(status).build()
                )
                .build();

        assertThatThrownBy(() -> session.enroll(SessionFixtures.USER))
                .isInstanceOf(CannotRegisterException.class);
    }
}