package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    @Test
    void 시작일_종료일() {
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 30);

        Session session = new Session.Builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assertThat(session.getStartDate()).isNotNull();
        assertThat(session.getEndDate()).isNotNull();
    }

    @Test
    void 이미지정보() {
        Session session = new Session.Builder()
                .image("image.jpeg")
                .build();

        assertThat(session.getImage()).isNotNull();
    }

    @Test
    void 무료강의() {
        Session session = new Session.Builder()
                .lectureType(LectureType.FREE)
                .build();

        assertThat(session.getLectureType()).isEqualTo(LectureType.FREE);
    }

    @Test
    void 유료강의() {
        Session session = new Session.Builder()
                .lectureType(LectureType.PAID)
                .build();

        assertThat(session.getLectureType()).isEqualTo(LectureType.PAID);
    }

    @Test
    void 준비중() {
        Session session = new Session.Builder()
                .lectureStatus(LectureStatus.PREPARING)
                .build();

        assertThat(session.getLectureStatus()).isEqualTo(LectureStatus.PREPARING);
    }

    @Test
    void 모집중() {
        Session session = new Session.Builder()
                .lectureStatus(LectureStatus.RECRUITING)
                .build();

        assertThat(session.getLectureStatus()).isEqualTo(LectureStatus.RECRUITING);
    }

    @Test
    void 종료() {
        Session session = new Session.Builder()
                .lectureStatus(LectureStatus.COMPLETED)
                .build();

        assertThat(session.getLectureStatus()).isEqualTo(LectureStatus.COMPLETED);
    }

    @Test
    void 모집중일때_수강신청() {
        Session session = new Session.Builder()
                .lectureStatus(LectureStatus.RECRUITING)
                .build();

        assertThatCode(() -> session.register(1L)).doesNotThrowAnyException();
    }

    @Test
    void 준비중일때_수강신청() {
        Session session = new Session.Builder()
                .lectureStatus(LectureStatus.PREPARING)
                .build();
        assertThatThrownBy(() -> session.register(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("수강신청은 모집중일때 가능합니다.");
    }

    @Test
    void 최대수강인원_초과불가() {
        Session session = new Session.Builder()
                .lectureStatus(LectureStatus.RECRUITING)
                .maxUser(2)
                .build();

        session.register(1L);
        session.register(2L);

        assertThatThrownBy(() -> session.register(3L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");

    }
}
