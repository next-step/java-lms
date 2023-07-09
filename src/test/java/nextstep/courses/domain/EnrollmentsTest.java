package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EnrollmentsTest {
    private static final NsUser jerry = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");

    @Test
    void 학생등록() {
        Enrollments enrollments = new Enrollments();
        enrollments.enroll(1L, jerry);

        assertThat(enrollments.getSize()).isEqualTo(1);
    }

    @Test
    void 중복등록불가() {
        Enrollments enrollments = new Enrollments();
        enrollments.enroll(1L, jerry);

        assertThatThrownBy(() -> {
            enrollments.enroll(1L, jerry);
        }).isInstanceOf(DuplicateStudentException.class).hasMessageContaining("이미 강의에 등록된 유저입니다.");
    }

    @Test
    void 수강승인및취소() {
        Enrollments enrollments = new Enrollments();
        enrollments.enroll(1L, jerry);
        enrollments.approve(1L, jerry);

        assertThat(enrollments.getApprovalCount(1L)).isEqualTo(1);

        enrollments.cancel(1L, jerry);
        assertThat(enrollments.getApprovalCount(1L)).isEqualTo(0);
    }

}
