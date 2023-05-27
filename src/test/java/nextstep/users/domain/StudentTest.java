package nextstep.users.domain;

import nextstep.courses.DuplicateSessionException;
import nextstep.courses.domain.SessionTest;
import nextstep.courses.domain.SessionsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StudentTest {

    public static Student student1 = new Student(NsUserTest.SANJIGI);
    public static Student student2 = new Student(NsUserTest.JAVAJIGI);

    @Test
    @DisplayName("수강 신청")
    void addSession() {
        student1.addSession(SessionTest.s1);

        assertThat(student1.getSessions()).isEqualTo(SessionsTest.ss1);
    }

    @Test
    @DisplayName("중복 수강 신청")
    void addSession_duplicateException() {
        student1.addSession(SessionTest.s1);

        assertThatThrownBy(() -> {
            student1.addSession(SessionTest.s1);
        }).isInstanceOf(DuplicateSessionException.class).hasMessageContaining("중복 기수 강의를 신청할 수 없습니다.");
    }
}
