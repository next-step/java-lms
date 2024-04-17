package nextstep.courses.domain.session;

import nextstep.courses.UnAuthorizedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.courses.domain.course.CourseTest.COURSE;
import static nextstep.courses.domain.coverImage.CoverImageTest.COVER_IMAGE_PNG;
import static nextstep.courses.domain.session.EnrollmentStatus.*;
import static nextstep.courses.domain.session.PeriodTest.PERIOD_OF_SESSION;
import static nextstep.courses.domain.session.SessionTypeTest.PAID_SESSION_TYPE;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {
    @Nested
    @DisplayName("status 업데이트 테스트")
    class UpdatingStatusTest {
        private Session session = new Session(11L, "유료 강의", "유료 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImages(List.of(COVER_IMAGE_PNG)), COURSE);
        private Enrollment enrollment = new Enrollment(session, SANJIGI);

        @Test
        @DisplayName("강의를 만든 사용자가 아닌 경우 UnAuthorizedException 발생한다.")
        void testFailCase() {
            assertThatThrownBy(() -> enrollment.approve(ZIPJIGI)).isExactlyInstanceOf(UnAuthorizedException.class);
            assertThatThrownBy(() -> enrollment.reject(ZIPJIGI)).isExactlyInstanceOf(UnAuthorizedException.class);
            assertThatThrownBy(() -> enrollment.wait(ZIPJIGI)).isExactlyInstanceOf(UnAuthorizedException.class);
        }

        @Nested
        @DisplayName("강의를 만든 사용자인 경우")
        class SuccessCaseTest {
            @Test
            @DisplayName("상태를 APPROVED로 변경한다.")
            void testApprove() {
                enrollment.approve(JAVAJIGI);
                assertThat(enrollment.getStatus()).isEqualTo(APPROVED);
            }

            @Test
            @DisplayName("상태를 REJECTED로 변경한다.")
            void testReject() {
                enrollment.reject(JAVAJIGI);
                assertThat(enrollment.getStatus()).isEqualTo(REJECTED);
            }

            @Test
            @DisplayName("상태를 REJECTED로 변경한다.")
            void testWait() {
                enrollment.wait(JAVAJIGI);
                assertThat(enrollment.getStatus()).isEqualTo(WAITING);
            }
        }
    }
}