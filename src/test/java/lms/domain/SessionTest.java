package lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @Test
    @DisplayName("강의 시작일과 종료일 유효성 체크")
    void validateSessionDateTest() {
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 5, 21);

        assertThatThrownBy(() -> new Session(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료 날짜가 시작 날짜보다 앞일 수 없습니다.");
    }

    @Test
    @DisplayName("강의 이미지 커버 수정 기능")
    void changeImageCoverTest() {
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 5, 23);
        Image imageCover = new Image();

        Session session = new Session(startDate, endDate, imageCover);

        Image changeCover = new Image();

        assertThat(session.changeImageCover(changeCover))
                .isEqualTo(changeCover);
    }

}
