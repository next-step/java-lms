package nextstep.courses.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void sessionHasStartAtAndEndAtTest() {
        new Session(LocalDate.now(), LocalDate.now());
    }

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    void sessionHasCoverImageTest() {
        new Session(LocalDate.now(), LocalDate.now());
    }

}