package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
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
        freeSession = Session.defaultOf(1L, course, LocalDate.of(2024, 4, 28), LocalDate.of(2024, 9, 7), new Image(10, ImageType.JPG, 300, 200), SessionPayType.FREE, 0, 3000L);
        paidSession = Session.defaultOf(2L, course, LocalDate.of(2024, 4, 28), LocalDate.of(2024, 9, 7), new Image(10, ImageType.JPG, 300, 200), SessionPayType.PAID, 1, 3000L);
    }

    @Test
    @DisplayName("모집중이 아닌 강의는 수강신청이 불가능한지 확인")
    void cannotRegister(){
        Payment payment = new Payment("1", 1L, 1L, 3000L);
        assertThatThrownBy(() -> freeSession.addStudent(NsUserTest.JAVAJIGI, payment)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의가 끝나면  수강신청이 불가능한지 확인")
    void cannotRegisterAfterSessionEnd(){
        Session endSession = Session.defaultOf(3L, course, LocalDate.of(2023, 4, 28), LocalDate.of(2023, 4, 29), new Image(10, ImageType.JPG, 300, 200), SessionPayType.FREE, 0, 3000L);
        Payment payment = new Payment("1", 1L, 1L, 3000L);
        assertThatThrownBy(() -> endSession.addStudent(NsUserTest.JAVAJIGI, payment)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없는지 확인")
    void limitStudentCapacity(){
        paidSession.openRegister();
        paidSession.addStudent(NsUserTest.SANJIGI, new Payment("1", 2L, 2L, 3000L));
        assertThatThrownBy(() -> paidSession.addStudent(NsUserTest.JAVAJIGI, new Payment("2", 1L, 1L, 3000L))).isInstanceOf(IllegalArgumentException.class);
    }
}
