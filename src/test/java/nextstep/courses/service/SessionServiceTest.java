package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionBuilder;
import nextstep.courses.domain.session.SessionCoverImage;
import nextstep.courses.domain.session.SessionDuration;
import nextstep.courses.domain.session.SessionPaymentType;
import nextstep.courses.domain.session.SessionRegistrationBuilder;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.student.Students;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@SpringBootTest
class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("세션 저장 및 조회")
    void test01() {
        LocalDateTime expectedCreatedAt = LocalDateTime.now();
        SessionDuration expectedDuration
                = new SessionDuration(expectedCreatedAt.plusDays(10L), expectedCreatedAt.plusDays(30L));
        SessionCoverImage expectedCoverImage = SessionCoverImage.create("http://test.com/image");
        
        sessionService.save(
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

        Session savedSession = sessionService.findById(2L);

        assertAll(
                () -> assertThat(savedSession.getDuration()).isEqualTo(expectedDuration),
                () -> assertThat(savedSession.getCoverImage()).isEqualTo(expectedCoverImage)
        );
    }

}
