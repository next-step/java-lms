package nextstep.sessions.service;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionBuilder;
import nextstep.sessions.domain.SessionCoverImage;
import nextstep.sessions.domain.SessionDuration;
import nextstep.sessions.domain.SessionPaymentType;
import nextstep.sessions.domain.SessionRecruitmentStatus;
import nextstep.sessions.domain.SessionRegistrationBuilder;
import nextstep.sessions.domain.SessionStatus;
import nextstep.students.domain.Students;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
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
    private long testID;

    @BeforeEach
    void setUp() {
        testID = 1L;
        sessionRegistrationService.save(
                SessionBuilder.aSession().
                        withId(testID)
                        .withDuration(new SessionDuration(LocalDateTime.now(), LocalDateTime.now()))
                        .withCoverImage(SessionCoverImage.create("http://test.com/image"))
                        .withPaymentType(SessionPaymentType.PAID)
                        .with(SessionRegistrationBuilder.aRegistration()
                                .withStatus(SessionStatus.PREPARING)
                                .withRecruitmentStatus(SessionRecruitmentStatus.RECRUITING)
                                .withStudents(new Students())
                                .withStudentCapacity(10))
                        .withCreatedAt(LocalDateTime.now())
                        .build());
    }

    @Test
    @DisplayName("세션 저장 및 조회")
    void test01() {
        ;

        Session savedSession = sessionRegistrationService.findById(testID);

        assertAll(
                () -> assertThat(savedSession.getPaymentType()).isEqualTo(SessionPaymentType.PAID),
                () -> assertThat(savedSession.getStatus()).isEqualTo(SessionStatus.PREPARING),
                () -> assertThat(savedSession.getRecruitmentStatus()).isEqualTo(SessionRecruitmentStatus.RECRUITING)
        );
    }

    @Test
    @DisplayName("수강등록을 정상 수행합니다.")
    void test02() {
        assertThatNoException()
                .isThrownBy(() -> sessionRegistrationService.register(NsUserTest.JAVAJIGI.getUserId(), testID));
    }

}
