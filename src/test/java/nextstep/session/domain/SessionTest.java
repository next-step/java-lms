package nextstep.session.domain;

import nextstep.session.NotProceedingException;
import nextstep.session.StudentNumberExceededException;
import nextstep.students.domain.Students;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @ParameterizedTest
    @EnumSource(value = ProgressStatus.class, names = {"END", "READY"})
    void 강의_수강신청은_모집중일_때만_가능하다(ProgressStatus status) {

        // given
        Session session = new Session(1L, 1L, status, RecruitmentStatus.RECRUITING);
        Students students = new Students(1L, 1L);

        // when
        assertThatThrownBy(
                () -> session.signUp(students))
                .isInstanceOf(NotProceedingException.class);
    }

    @Test
    void 강의는_강의_최대_수강_인원을_초과할_수_없다() {

        // given
        Session session = new Session(1L, 0L, ProgressStatus.PROCEEDING, RecruitmentStatus.RECRUITING);
        Students students = new Students(1L, 1L);

        // when
        assertThatThrownBy(
                () -> session.signUp(students))
                .isInstanceOf(StudentNumberExceededException.class);
    }

    @Test
    void 수강신청_성공() throws StudentNumberExceededException, NotProceedingException {

        // given
        Session session = new Session(2L, 2L, ProgressStatus.PROCEEDING, RecruitmentStatus.RECRUITING);
        Students students = new Students(2L, 1L);

        // when
        session.signUp(students);

        // then
        assertThat(session.getStudents()).contains(students);
    }
}
