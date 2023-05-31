package nextstep.sessions.domain;

import nextstep.sessions.domain.SessionStudents;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SessionStudentsTest {

    @ParameterizedTest(name = "nsUserId를 이용하여 수강 중인 학생인지 확인")
    @ValueSource(longs = {1L, 2L})
    void name(Long nsUserId) {
        SessionStudents sessionStudents = new SessionStudents(Arrays.asList(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        assertTrue(sessionStudents.contains(nsUserId));
    }
}
