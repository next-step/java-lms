package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private  Session session ;

    @BeforeEach
    void setup() {
        session = new Session();
    }

    @Test
    @DisplayName("강의 객체 생성 case 1 - 시작일 종료일 필드 여부")
    void session_create_test(){
        assertThat(session)
                .hasFieldOrProperty("startDate")
                .hasFieldOrProperty("endDate");
    }
}
