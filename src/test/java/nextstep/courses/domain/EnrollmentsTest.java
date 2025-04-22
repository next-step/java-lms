package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentsTest {

    private final Member member1 = new Member(1L, "홍길동", "hong@example.com");
    private final Member member2 = new Member(2L, "이몽룡", "lee@example.com");

    private final Image validImage = new Image(500f, "png", "cdn.com", 600, 400);
    private final Images validImages = new Images(List.of(validImage));

    private Session createSession() {
        return new Session(
                "강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                0L,
                0,
                10,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );
    }

    @Test
    @DisplayName("Enrollment를 추가하면 리스트에 포함된다")
    void addEnrollment_stores_enrollment() {
        Enrollments enrollments = new Enrollments();
        Session session = createSession();
        Enrollment enrollment = new Enrollment(member1, session);

        enrollments.addEnrollment(enrollment);

        assertThat(enrollments.getValues()).contains(enrollment);
    }

    @Test
    @DisplayName("해당 멤버가 수강 신청한 경우 isEnrolledBy()는 true를 반환한다")
    void isEnrolledBy_returns_true_for_existing_member() {
        Enrollments enrollments = new Enrollments();
        Session session = createSession();
        enrollments.addEnrollment(new Enrollment(member1, session));

        assertThat(enrollments.isEnrolledBy(member1)).isTrue();
    }

    @Test
    @DisplayName("해당 멤버가 수강 신청하지 않은 경우 isEnrolledBy()는 false를 반환한다")
    void isEnrolledBy_returns_false_for_non_enrolled_member() {
        Enrollments enrollments = new Enrollments();
        assertThat(enrollments.isEnrolledBy(member1)).isFalse();
    }

    @Test
    @DisplayName("findByMember()는 수강 신청한 멤버의 Enrollment를 반환한다")
    void findByMember_returns_enrollment() {
        Enrollments enrollments = new Enrollments();
        Session session = createSession();
        Enrollment enrollment = new Enrollment(member1, session);
        enrollments.addEnrollment(enrollment);

        Enrollment found = enrollments.findByMember(member1);

        assertThat(found).isEqualTo(enrollment);
    }

    @Test
    @DisplayName("findByMember()는 수강 신청하지 않은 멤버일 경우 예외를 던진다")
    void findByMember_throws_for_non_existing_member() {
        Enrollments enrollments = new Enrollments();

        assertThatThrownBy(() -> enrollments.findByMember(member2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 회원의 수강 신청이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("getValues()는 불변 리스트를 반환한다")
    void getValues_returns_unmodifiable_list() {
        Enrollments enrollments = new Enrollments();
        Session session = createSession();
        enrollments.addEnrollment(new Enrollment(member1, session));

        List<Enrollment> values = enrollments.getValues();

        assertThatThrownBy(() -> values.add(new Enrollment(member2, session)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
