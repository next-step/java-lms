package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionParticipant;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SessionParticipantTest {

    @ParameterizedTest
    @CsvSource(value = {"31, true", "29, false"})
    void 강의_수강인원_테스트(int studentCount, boolean result) {
        Students students = SessionFixture.students(studentCount);
        int sessionsMaximumCount = 30;
        SessionParticipant sessionParticipant = new SessionParticipant(sessionsMaximumCount, students);

        Assertions.assertThat(sessionParticipant.isFullSession()).isEqualTo(result);
    }

    @Test
    void 강의_수강인원_증가_테스트() {
        int studentCount = 20;
        int sessionsMaximumCount = 30;
        Students students = SessionFixture.students(studentCount);
        SessionParticipant sessionParticipant = new SessionParticipant(sessionsMaximumCount, students);

        Student laminett = new Student(1, "laminett");
        sessionParticipant.participateStudent(laminett);

        Assertions.assertThat(sessionParticipant.getStudentsCount()).isEqualTo(++studentCount);
    }
}
