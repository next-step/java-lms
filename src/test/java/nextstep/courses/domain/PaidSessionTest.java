package nextstep.courses.domain;

import nextstep.courses.CannotSignUpException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaidSessionTest {
    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다. ")
    void sessionStudentTest() throws CannotSignUpException {
        PaidSession paidSession = new PaidSession("step4", new Course("TDD", 1L), 1);

        paidSession.signUp();
        assertThrows(CannotSignUpException.class, () -> paidSession.signUp());
    }
}
