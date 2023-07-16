package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EnrollmentsTest {
    private NsUser jerry = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");
    private Enrollments enrollments = new Enrollments();

    @Test
    void 학생등록() {
        enrollments.enroll(1L, jerry);
        assertThat(enrollments.getSize()).isEqualTo(1);
    }

    @Test
    void 중복등록불가() {
        enrollments.enroll(1L, jerry);
        assertThatThrownBy(() -> {
            enrollments.enroll(1L, jerry);
        }).isInstanceOf(DuplicateStudentException.class).hasMessageContaining("이미 강의에 등록된 유저입니다.");
    }
    
    @Test
    void 수강인원FULL() {
        enrollments.enroll(1L, jerry);
        assertThat(enrollments.isFull(1)).isTrue();
    }

}
