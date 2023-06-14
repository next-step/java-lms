package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    @Test
    void 시작일_종료일() {
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 30);

        Session session = new Session(startDate, endDate);

        assertThat(session.getStartDate()).isNotNull();
        assertThat(session.getEndDate()).isNotNull();
    }

    @Test
    void 이미지정보() {
        Session session = new Session("image.jpg");

        assertThat(session.getImage()).isNotNull();
    }

    @Test
    void 무료강의() {
        Session session = new Session(LectureType.FREE);

        assertThat(session.getLectureType()).isEqualTo(LectureType.FREE);
    }

    @Test
    void 유료강의() {
        Session session = new Session(LectureType.PAID);

        assertThat(session.getLectureType()).isEqualTo(LectureType.PAID);
    }

    @Test
    void 준비중() {
        Session session = new Session(LectureStatus.PREPARING);

        assertThat(session.getLectureStatus()).isEqualTo(LectureStatus.PREPARING);
    }

    @Test
    void 모집중() {
        Session session = new Session(LectureStatus.RECRUITING);

        assertThat(session.getLectureStatus()).isEqualTo(LectureStatus.RECRUITING);
    }

    @Test
    void 종료() {
        Session session = new Session(LectureStatus.COMPLETED);

        assertThat(session.getLectureStatus()).isEqualTo(LectureStatus.COMPLETED);
    }

    @Test
    void 모집중일때_수강신청() {
        Session session = new Session(LectureStatus.RECRUITING);

        assertThatCode(() -> session.register(1L)).doesNotThrowAnyException();
    }

    @Test
    void 준비중일때_수강신청() {
        Session session = new Session(LectureStatus.PREPARING);
        assertThatThrownBy(() -> session.register(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("수강신청은 모집중일때 가능합니다.");
    }

    @Test
    void 최대수강인원_초과불가() {
        Session session = new Session(LectureStatus.RECRUITING);
        session.register(1L);
        session.register(2L);

        assertThatThrownBy(() -> session.register(3L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");

    }
}
