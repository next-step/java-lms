package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nextstep.courses.domain.course.CourseTest.COURSE;
import static nextstep.courses.domain.coverImage.CoverImageTest.COVER_IMAGE_PNG;
import static nextstep.courses.domain.session.PeriodTest.PERIOD_OF_SESSION;
import static nextstep.courses.domain.session.SessionTypeTest.PAID_SESSION_TYPE;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentsTest {
    private Session session = new Session(11L, "유료 강의", "유료 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImages(List.of(COVER_IMAGE_PNG)), COURSE);

    @Test
    @DisplayName("numberOfCurrentEnrollment(): 현재 수강 승인된 수강자들의 수를 반환한다.")
    void testNumberOfCurrentEnrollment() {
        Enrollment enrollmentOfSANJIGI = new Enrollment(session, SANJIGI);
        Enrollment enrollmentOfZIPJIGI = new Enrollment(session, ZIPJIGI);
        enrollmentOfZIPJIGI.approve(JAVAJIGI);

        Enrollments enrollments = new Enrollments(List.of(enrollmentOfSANJIGI, enrollmentOfZIPJIGI));

        assertThat(enrollments.numberOfCurrentEnrollment()).isEqualTo(1);
    }

    @Nested
    @DisplayName("add() 테스트")
    class AddTest {
        private Enrollment enrollmentOfSANJIGI = new Enrollment(session, SANJIGI);
        private Enrollment enrollmentOfZIPJIGI = new Enrollment(session, ZIPJIGI);
        private Enrollments enrollments = new Enrollments(new ArrayList<>(List.of(enrollmentOfSANJIGI, enrollmentOfZIPJIGI)));

        @Test
        @DisplayName("수강 승인 여부와 상관없이 사용자가 이미 수강신청을 한 경우 CannotEnrollException이 발생한다.")
        void testFailCase() {
            assertThatThrownBy(() -> enrollments.add(session, SANJIGI)).isExactlyInstanceOf(CannotEnrollException.class);
        }

        @Test
        @DisplayName("수강 승인 여부와 상관없이 사용자가 이미 수강신청을 하지 않은 경우 수강 신청이 된다.")
        void testSuccessCase() {
            enrollments.add(session, BAPZIGI);
            assertThat(enrollments.getEnrollments().size()).isEqualTo(3);
        }
    }
}
