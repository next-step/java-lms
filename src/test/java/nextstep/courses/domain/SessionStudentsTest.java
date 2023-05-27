package nextstep.courses.domain;

import nextstep.courses.exception.SessionRegistrationException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;


class SessionStudentsTest {

    private SessionStudents sessionStudentsMaxTwo = new SessionStudents(2);

    @BeforeEach
    void setUp() {
        sessionStudentsMaxTwo.addStudent(NsUserTest.SANJIGI);
        sessionStudentsMaxTwo.addStudent(NsUserTest.JAVAJIGI);
    }

    @Test
    void countStudents() {
        assertThat(sessionStudentsMaxTwo.countStudents()).isEqualTo(2);
    }

    @Test
    void checkExceedLimit_정원초과() {
        assertThatThrownBy(() -> {
            sessionStudentsMaxTwo.checkExceedLimit();
        }).isInstanceOf(SessionRegistrationException.class)
                .hasMessageContaining("정원이 초과되었습니다.");
    }
}