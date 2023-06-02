package nextstep.sessions.service;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionBuilder;
import nextstep.sessions.domain.SessionCoverImage;
import nextstep.sessions.domain.SessionDuration;
import nextstep.sessions.domain.SessionPaymentType;
import nextstep.sessions.domain.SessionRegistrationBuilder;
import nextstep.sessions.domain.SessionStatus;
import nextstep.students.domain.Students;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@SpringBootTest
class SessionRegistrationServiceTest {

    @Autowired
    private SessionRegistrationService sessionRegistrationService;

    @Test
    @DisplayName("세션 저장 및 조회")
    void test01() {
        LocalDateTime expectedCreatedAt = LocalDateTime.now();
        SessionDuration expectedDuration
                = new SessionDuration(expectedCreatedAt.plusDays(10L), expectedCreatedAt.plusDays(30L));
        SessionCoverImage expectedCoverImage = SessionCoverImage.create("http://test.com/image");

        sessionRegistrationService.save(
                SessionBuilder.aSession()
                        .withDuration(expectedDuration)
                        .withCoverImage(expectedCoverImage)
                        .withPaymentType(SessionPaymentType.PAID)
                        .with(SessionRegistrationBuilder.aRegistration()
                                .withStatus(SessionStatus.PREPARING)
                                .withStudents(new Students())
                                .withStudentCapacity(10))
                        .withCreatedAt(expectedCreatedAt)
                        .build());

        Session savedSession = sessionRegistrationService.findById(2L);

        assertAll(
                () -> assertThat(savedSession.getDuration()).isEqualTo(expectedDuration),
                () -> assertThat(savedSession.getCoverImage()).isEqualTo(expectedCoverImage)
        );
    }

    @Test
    @DisplayName("수강등록을 정상 수행합니다.")
    void test02() {
        assertThatNoException()
                .isThrownBy(() -> sessionRegistrationService.register(NsUserTest.SANJIGI.getUserId(), 1L));

    }

}
