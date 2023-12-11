package nextstep.courses.domain;

import nextstep.courses.domain.attendee.FreeAttendees;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.image.ImageInformation;
import nextstep.courses.domain.image.ImageSize;
import nextstep.courses.domain.image.Images;
import nextstep.courses.domain.session.*;
import nextstep.courses.exception.CanNotApplyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.courses.domain.image.ImageFormat.JPG;
import static nextstep.courses.domain.session.Recruitment.*;
import static nextstep.courses.domain.session.SessionStatus.*;
import static org.assertj.core.api.Assertions.*;


class SessionTest {

    @DisplayName("모집 중이 아닌 강의는 수강신청할 수 없다.")
    @Test
    void throw_exception_if_session_is_not_recruiting() {
        Period period = new Period(LocalDate.now(),
                                   LocalDate.now().plusDays(1));
        ImageInformation imageInformation = new ImageInformation(new ImageSize(300.0, 200.0),
                                                                 100,
                                                                 JPG);
        Image image = new Image(1L, imageInformation);
        SessionInformation information = new SessionInformation(PREPARING, period, new Images(image), NOT_RECRUITING);
        Enrollment freeEnrollment = new FreeEnrollment(new FreeAttendees());
        EnrollmentSession session = new EnrollmentSession(1L, information, freeEnrollment);

        assertThatThrownBy(() -> session.enroll(100L, 1L))
                .isInstanceOf(CanNotApplyException.class);
    }

}