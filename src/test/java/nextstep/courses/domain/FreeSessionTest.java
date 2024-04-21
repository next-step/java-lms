package nextstep.courses.domain;

import nextstep.sessions.domain.*;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class FreeSessionTest {

    Session session;

    @BeforeEach
    void setUp() {
        // 강의의 시작일, 종료일
        LocalDate startDate = LocalDate.of(2024, 04, 8);
        LocalDate endDate = LocalDate.of(2024, 04, 10);
        SessionPeriod sessionPeriod = new SessionPeriod(startDate, endDate);

        // 강의 커버 이미지 객체
        int imageByte = 1000; // kb 단위
        String imageType = "gif";
        int imageWidth = 300;
        int imageHeight = 200;
        SessionImage sessionImage = new SessionImage(imageByte, imageType, imageWidth, imageHeight);

        session = new FreeSession(0L, 0L, sessionPeriod, sessionImage, SessionStatus.PREPARING);
    }

    @Test
    @DisplayName("무료 강의의 경우 강의 상태가 모집 중이 아니라면 수강 신청이 불가능하다.")
    void freeSessionTest() {
        assertThatThrownBy(() -> session.signUp(new NsUser()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수강 모집 중이 아닙니다.");
    }

}
