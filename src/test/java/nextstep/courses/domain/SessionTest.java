package nextstep.courses.domain;

import nextstep.courses.SessionStateNotOnException;
import nextstep.courses.StudentMaxException;
import nextstep.users.domain.StudentTest;
import nextstep.users.domain.StudentsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    public static Session s1 = Session.of("lotto1", "image1", 1, Cost.FREE, State.READY, 30);
    public static Session s2 = Session.of("lotto2", "image2", 2, Cost.FREE, State.ON, 1);
    public static Session s3 = Session.of("lotto3", "image3", 3, Cost.FREE, State.OFF, 30);

    @Test
    @DisplayName("학생 등록")
    void addStudent() {
        assertThat(s2.addStudent(StudentTest.student1)).isEqualTo(StudentsTest.students);
    }

    @Test
    @DisplayName("학생 정원 초과")
    void addStudent_maxUserException() {
        s2.addStudent(StudentTest.student2);
        assertThatThrownBy(() -> {
            s2.addStudent(StudentTest.student1);
        }).isInstanceOf(StudentMaxException.class).hasMessageContaining("정원 초과하여 신청할 수 없습니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 준비중")
    void addStudent_stateOnException() {
        assertThatThrownBy(() -> {
            s1.addStudent(StudentTest.student1);
        }).isInstanceOf(SessionStateNotOnException.class).hasMessageContaining("준비 중인 강의입니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 종료")
    void addStudent_stateOffException() {
        assertThatThrownBy(() -> {
            s3.addStudent(StudentTest.student1);
        }).isInstanceOf(SessionStateNotOnException.class).hasMessageContaining("모집 종료된 강의입니다.");
    }
}
