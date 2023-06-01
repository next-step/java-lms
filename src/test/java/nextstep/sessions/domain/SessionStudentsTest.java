package nextstep.sessions.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionStudentsTest {

    private final SessionStudents sessionStudents = new SessionStudents(new HashSet<>(Arrays.asList(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)), 4);

    @ParameterizedTest(name = "nsUserId를 이용하여 수강 중인 학생인지 확인")
    @ValueSource(longs = {1L, 2L})
    void test1(Long nsUserId) {
        assertTrue(sessionStudents.contains(nsUserId));
    }

    @Test
    @DisplayName(value = "중복 수강 신청할 경우 검사")
    void test2() {
        assertThatThrownBy(() -> {
            sessionStudents.enrollStudent(NsUserTest.JAVAJIGI);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
