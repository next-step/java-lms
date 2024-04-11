package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionStatusEnumTest {

    @Test
    @DisplayName("강의 상태 '준비중' 생성")
    void testSessionStatus_ShouldReturnPENDING() {
        // when
        SessionStatusEnum sessionStatusEnum = SessionStatusEnum.PENDING;

        // then
        assertEquals(sessionStatusEnum, SessionStatusEnum.PENDING);
        assertFalse(sessionStatusEnum.isSessionOpened());
    }

    @Test
    @DisplayName("강의 상태 '모집중' 생성")
    void testSessionStatus_ShouldReturnOPEN() {
        // when
        SessionStatusEnum sessionStatusEnum = SessionStatusEnum.OPEN;

        // then
        assertEquals(sessionStatusEnum, SessionStatusEnum.OPEN);
        assertTrue(sessionStatusEnum.isSessionOpened());
    }

    @Test
    @DisplayName("강의 상태 '종료' 생성")
    void testSessionStatus_ShouldReturnCLOSED() {
        // when
        SessionStatusEnum sessionStatusEnum = SessionStatusEnum.CLOSED;

        // then
        assertEquals(sessionStatusEnum, SessionStatusEnum.CLOSED);
        assertFalse(sessionStatusEnum.isSessionOpened());
    }

}
