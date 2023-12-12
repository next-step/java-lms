package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStudentCountTest {
    @Test
    void sessionStudentCount_수강인원증가확인(){
        SessionStudentCount sessionStudentCount = new SessionStudentCount(10);
        sessionStudentCount.addStudent();
        assertThat(sessionStudentCount.getStudentCount()).isEqualTo(1);
    }
}
