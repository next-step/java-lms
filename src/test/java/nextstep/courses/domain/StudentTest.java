package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentTest {

    @Test
    @DisplayName("학생은 수강료를 결제할 수 있다.")
    void pay() {
        Student student = new Student(800_000L);
        student.pay(800_000L);
        assertEquals(0L, student.getAmount());
    }

    @Test
    @DisplayName("학생은 돈이 부족하면 수강료를 결제할 수 없다.")
    void payWithNotEnoughMoney() {
        Student student = new Student(400_000L);
        assertThrows(IllegalArgumentException.class, () -> student.pay(800_000L));
    }

}
