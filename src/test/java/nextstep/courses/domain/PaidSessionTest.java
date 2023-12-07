package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaidSessionTest {
    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다. ")
    void sessionStudentTest() {

        PaidSession paidSession = new PaidSession("step4", new Course("TDD", 1L));

    }
}
