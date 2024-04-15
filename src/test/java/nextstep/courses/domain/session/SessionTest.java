package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.course.CourseTest.COURSE;
import static nextstep.courses.domain.session.CoverImageTest.COVER_IMAGE_PNG;
import static nextstep.courses.domain.session.PeriodTest.PERIOD_OF_SESSION;
import static nextstep.courses.domain.session.SessionGatheringStatus.GATHERING;
import static nextstep.courses.domain.session.SessionTypeTest.FREE_SESSION_TYPE;
import static nextstep.courses.domain.session.SessionTypeTest.PAID_SESSION_TYPE;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    @Nested
    @DisplayName("enroll() 테스트")
    class EnrollTest {
        @Nested
        @DisplayName("수강 신청을 실패한 경우 테스트")
        class FailCaseTest {
            @Test
            @DisplayName("강의 상태가 모집중이 아닌 경우 CannotEnrollException이 발생한다.")
            void testInvalidSessionStatus() {
                Session session = new Session(11L, "무료 강의", "무료 강의다", FREE_SESSION_TYPE, PERIOD_OF_SESSION, COVER_IMAGE_PNG, COURSE);

                assertThatThrownBy(() -> session.enroll(JAVAJIGI, new Payment("p1", 11L, JAVAJIGI.getId(), 0L)))
                        .isExactlyInstanceOf(CannotEnrollException.class);
            }

            @Test
            @DisplayName("이미 신청한 강의인 경우 CannotEnrollException이 발생한다.")
            void testDuplicateEnroll() {
                NsUser user = JAVAJIGI;
                Payment payment = new Payment("p1", 11L, user.getId(), 0L);

                Session session = new Session(11L, "무료 강의", "무료 강의다", FREE_SESSION_TYPE, PERIOD_OF_SESSION, COVER_IMAGE_PNG, COURSE);
                session.updateSessionGatheringStatusAs(GATHERING);
                session.enroll(user, payment);

                assertThatThrownBy(() -> session.enroll(user, payment))
                        .isExactlyInstanceOf(CannotEnrollException.class);
            }

            @Test
            @DisplayName("유료 강의면서 최대 수강 인원인 경우 CannotEnrollException이 발생한다.")
            void testFullEnrolledPaidSession() {
                Session session = new Session(11L, "유료 강의", "유료 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, COVER_IMAGE_PNG, COURSE);
                session.updateSessionGatheringStatusAs(GATHERING);
                session.enroll(JAVAJIGI, new Payment("p1", 11L, JAVAJIGI.getId(), 100L));
                session.enroll(SANJIGI, new Payment("p2", 11L, SANJIGI.getId(), 100L));

                assertThatThrownBy(() -> session.enroll(ZIPJIGI, new Payment("p3", 11L, ZIPJIGI.getId(), 100L)))
                        .isExactlyInstanceOf(CannotEnrollException.class);
            }

            @Test
            @DisplayName("유료 강의면서 결제한 금액과 수강료가 일치하지 않는 경우 CannotEnrollException이 발생한다.")
            void testInvalidPaymentPaidSession() {
                Session session = new Session(11L, "유료 강의", "유료 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, COVER_IMAGE_PNG, COURSE);
                session.updateSessionGatheringStatusAs(GATHERING);

                assertThatThrownBy(() -> session.enroll(JAVAJIGI, new Payment("p1", 11L, JAVAJIGI.getId(), 0L)))
                        .isExactlyInstanceOf(CannotEnrollException.class);
            }
        }

        @Nested
        @DisplayName("수강 신청을 성공한 경우 테스트")
        class SuccessCase {
            @Test
            @DisplayName("검증 조건을 통과한 경우 정상적으로 무료 강의가 수강 신청되는 것을 확인할 수 있습니다.")
            void testFreeSession() {
                Session session = new Session(11L, "무료 강의", "무료 강의다", FREE_SESSION_TYPE, PERIOD_OF_SESSION, COVER_IMAGE_PNG, COURSE);
                session.updateSessionGatheringStatusAs(GATHERING);

                assertThatNoException().isThrownBy(() -> session.enroll(JAVAJIGI, new Payment("p1", 11L, JAVAJIGI.getId(), 0L)));
                assertThatNoException().isThrownBy(() -> session.enroll(SANJIGI, new Payment("p2", 11L, SANJIGI.getId(), 0L)));
                assertThatNoException().isThrownBy(() -> session.enroll(ZIPJIGI, new Payment("p3", 11L, ZIPJIGI.getId(), 0L)));
            }

            @Test
            @DisplayName("검증 조건을 통과한 경우 정상적으로 유료 강의가 수강 신청되는 것을 확인할 수 있습니다.")
            void testPaidSession() {
                Session session = new Session(11L, "유료 강의", "유료 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, COVER_IMAGE_PNG, COURSE);
                session.updateSessionGatheringStatusAs(GATHERING);

                assertThatNoException().isThrownBy(() -> session.enroll(JAVAJIGI, new Payment("p1", 11L, JAVAJIGI.getId(), 100L)));
                assertThatNoException().isThrownBy(() -> session.enroll(SANJIGI, new Payment("p2", 11L, SANJIGI.getId(), 100L)));
            }
        }
    }
}
