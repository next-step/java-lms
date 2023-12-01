package nextstep.courses.domain;

import nextstep.courses.domain.type.ApplyStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InstructorTest {

    @Test
    @DisplayName("강사는 지원을 수락할 수 있다")
    public void instructor_approve_apply() {
        Session session = session();
        Instructor instructor = new Instructor(Arrays.asList(session));
        instructor.approve(0L, 1L);

        assertThat(session.applies().of(1L)).extracting(Apply::status).isEqualTo(ApplyStatus.APPROVAL);
    }

    @Test
    @DisplayName("강사는 지원을 거절할 수 있다")
    public void instructor_approve_refuse() {
        Session session = session();
        Instructor instructor = new Instructor(Arrays.asList(session));
        instructor.refuse(0L, 1L);

        assertThat(session.applies().of(1L)).extracting(Apply::status).isEqualTo(ApplyStatus.REFUSAL);
    }

    private Session session() {
        Duration duration = new Duration(LocalDate.now(), LocalDate.now());
        Image image = new Image(1, "JPG", 300, 200);
        Session session = new FreeSession(duration, new Images(Arrays.asList(image)));
        session.addStudent(NsUserTest.JAVAJIGI, LocalDateTime.of(2023, 12, 12, 12, 12, 12));
        return session;
    }

}
