package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {


    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void sessionHasStartAndEndDate() {
        Session session = new Session(LocalDateTime.of(2020, 1, 1, 0, 0)
                , LocalDateTime.of(2020, 2, 1, 0, 0));
        assertThat(session)
                .hasFieldOrProperty("startDate")
                .hasFieldOrProperty("endDate");

    }

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    void sessionHasCoverUrl() {
        Session session = new Session();
        assertThat(session).hasFieldOrProperty("coverImage");
    }

}
