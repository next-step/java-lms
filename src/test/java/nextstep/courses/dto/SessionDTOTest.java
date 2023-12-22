package nextstep.courses.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.ImageExtension;
import nextstep.courses.domain.SessionPaymentType;
import nextstep.courses.domain.SessionStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionDTOTest {
    @Test
    @DisplayName("SessionDTO 생성")
    void create() {
        CoverImageDTO coverImageDTO = new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D);
        DurationDTO durationDTO = new DurationDTO(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        SessionPaymentDTO paymentDTO = new SessionPaymentDTO(SessionPaymentType.PAID, 10000L);
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO(new NsUsersDTO(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)), new NsUserLimitDTO(10));
        assertThat(new SessionDTO(1L, 1L, coverImageDTO, durationDTO, paymentDTO, SessionStatus.ENROLLING, enrollmentDTO, LocalDateTime.now(), LocalDateTime.now())).isInstanceOf(SessionDTO.class);
    }

    @Test
    @DisplayName("id 반환")
    void getId() {
        CoverImageDTO coverImageDTO = new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D);
        DurationDTO durationDTO = new DurationDTO(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        SessionPaymentDTO paymentDTO = new SessionPaymentDTO(SessionPaymentType.PAID, 10000L);
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO(new NsUsersDTO(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)), new NsUserLimitDTO(10));
        assertThat(new SessionDTO(1L, 1L, coverImageDTO, durationDTO, paymentDTO, SessionStatus.ENROLLING, enrollmentDTO, LocalDateTime.now(), LocalDateTime.now()).getId())
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("커버 사진 반환")
    void getCoverImageDTO() {
        CoverImageDTO coverImageDTO = new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D);
        DurationDTO durationDTO = new DurationDTO(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        SessionPaymentDTO paymentDTO = new SessionPaymentDTO(SessionPaymentType.PAID, 10000L);
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO(new NsUsersDTO(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)), new NsUserLimitDTO(10));
        assertThat(new SessionDTO(1L,1L, coverImageDTO, durationDTO, paymentDTO, SessionStatus.ENROLLING, enrollmentDTO, LocalDateTime.now(), LocalDateTime.now()).getCoverImageDTO())
                .isInstanceOf(CoverImageDTO.class);
    }

    @Test
    @DisplayName("기간 반환")
    void getDurationDTO() {
        CoverImageDTO coverImageDTO = new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D);
        DurationDTO durationDTO = new DurationDTO(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        SessionPaymentDTO paymentDTO = new SessionPaymentDTO(SessionPaymentType.PAID, 10000L);
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO(new NsUsersDTO(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)), new NsUserLimitDTO(10));
        assertThat(new SessionDTO(1L,1L, coverImageDTO, durationDTO, paymentDTO, SessionStatus.ENROLLING, enrollmentDTO, LocalDateTime.now(), LocalDateTime.now()).getDurationDTO())
                .isInstanceOf(DurationDTO.class);
    }

    @Test
    @DisplayName("강의 결제 방식 반환")
    void getSessionPaymentDTO() {
        CoverImageDTO coverImageDTO = new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D);
        DurationDTO durationDTO = new DurationDTO(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        SessionPaymentDTO paymentDTO = new SessionPaymentDTO(SessionPaymentType.PAID, 10000L);
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO(new NsUsersDTO(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)), new NsUserLimitDTO(10));
        assertThat(new SessionDTO(1L,1L, coverImageDTO, durationDTO, paymentDTO, SessionStatus.ENROLLING, enrollmentDTO, LocalDateTime.now(), LocalDateTime.now()).getSessionPaymentDTO())
                .isInstanceOf(SessionPaymentDTO.class);
    }

    @Test
    @DisplayName("강의 결제 방식 반환")
    void getSessionStatus() {
        CoverImageDTO coverImageDTO = new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D);
        DurationDTO durationDTO = new DurationDTO(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        SessionPaymentDTO paymentDTO = new SessionPaymentDTO(SessionPaymentType.PAID, 10000L);
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO(new NsUsersDTO(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)), new NsUserLimitDTO(10));
        assertThat(new SessionDTO(1L,1L, coverImageDTO, durationDTO, paymentDTO, SessionStatus.ENROLLING, enrollmentDTO, LocalDateTime.now(), LocalDateTime.now()).getSessionStatus())
                .isInstanceOf(SessionStatus.class);
    }

    @Test
    @DisplayName("강의 결제 방식 반환")
    void getEnrollmentDTO() {
        CoverImageDTO coverImageDTO = new CoverImageDTO("pobi.png", ImageExtension.PNG, 500L, 300D, 200D);
        DurationDTO durationDTO = new DurationDTO(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        SessionPaymentDTO paymentDTO = new SessionPaymentDTO(SessionPaymentType.PAID, 10000L);
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO(new NsUsersDTO(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)), new NsUserLimitDTO(10));
        assertThat(new SessionDTO(1L,1L, coverImageDTO, durationDTO, paymentDTO, SessionStatus.ENROLLING, enrollmentDTO, LocalDateTime.now(), LocalDateTime.now()).getEnrollmentDTO())
                .isInstanceOf(EnrollmentDTO.class);
    }
}
