package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    static final Course course = new Course();
    Session freeSession;
    Session paidSession;

    @BeforeEach
    void createSession(){
        freeSession = new Session(course, LocalDate.of(2024, 4, 28), LocalDate.of(2024, 9, 7), new Image(10, ImageType.JPG, 300, 200), SessionPayType.FREE, 0);
        paidSession = new Session(course, LocalDate.of(2024, 4, 28), LocalDate.of(2024, 9, 7), new Image(10, ImageType.JPG, 300, 200), SessionPayType.PAID, 1);
    }

    @Test
    @DisplayName("모집중이 아닌 강의는 수강신청이 불가능한지 확인")
    void cannotRegister(){
        assertThatThrownBy(() -> freeSession.addStudent(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없는지 확인")
    void limitStudentCapacity(){
        paidSession.openRegister();
        paidSession.addStudent(NsUserTest.SANJIGI);
        assertThatThrownBy(() -> paidSession.addStudent(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }
}
