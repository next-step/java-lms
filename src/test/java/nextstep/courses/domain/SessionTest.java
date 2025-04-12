package nextstep.courses.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void sessionHasStartAtAndEndAtTest() {
        var session = new Session(LocalDate.now(), LocalDate.now());
    }

}