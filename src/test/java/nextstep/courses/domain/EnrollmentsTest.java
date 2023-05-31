package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentException;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EnrollmentsTest {
    private static final NsUser jerry = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");

    @Test
    void 학생등록() {
        Enrollments enrollments = new Enrollments();

        enrollments.enroll(jerry, 1L);

        Assertions.assertThat(enrollments.getSize()).isEqualTo(1);
    }

    @Test
    void 중복등록불가테스트() {
        Enrollments enrollments = new Enrollments();

        enrollments.enroll(jerry, 1L);

        assertThatThrownBy(() -> {
            enrollments.enroll(jerry, 1L);
        }).isInstanceOf(DuplicateStudentException.class).hasMessageContaining("이미 강의에 등록된 유저입니다.");
    }

}
