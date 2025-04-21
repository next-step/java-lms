package nextstep.courses.domain;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionStatus;
import nextstep.courses.domain.model.Student;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentTest {

    public static Student createStudent(Long balance) {
        Session session = SessionTest.createFreeSession(SessionStatus.OPEN);
        return new Student(NsUserTest.createNsUser(3L, balance), session);
    }

    @Test
    @DisplayName("학생은 수강료를 결제할 수 있다.")
    void pay() {
        Student student = createStudent(800_000L);
        assertThatCode(() -> student.pay(800_000L)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("학생은 돈이 부족하면 수강료를 결제할 수 없다.")
    void payWithNotEnoughMoney() {
        Student student = createStudent(400_000L);
        assertThrows(IllegalArgumentException.class, () -> student.pay(800_000L));
    }

}
