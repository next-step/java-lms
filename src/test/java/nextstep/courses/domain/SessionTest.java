package nextstep.courses.domain;

import nextstep.users.domain.Email;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    Session session;

    @BeforeEach
    void setSession() {
        LocalDate start = LocalDate.of(2023, 6, 15);
        LocalDate end = LocalDate.of(2023, 6, 15);
        session = new Session(1L, "coby.jpg", start, end);
        session.changeToStatus(SessionType.RECRUITING);
    }

    @Test
    void 생성자_테스트() {
        LocalDate start = LocalDate.of(2023, 6, 15);
        LocalDate end = LocalDate.of(2023, 6, 15);
        Session session1 = new Session(1L, "coby.jpg", start, end);

        assertThat(session1.getId()).isEqualTo(1L);
    }

    @Test
    void 강의_무료값_체크() {
        session.switchToPaidCourse();
        session.switchToFreeCourse();
        assertThat(session.getFree()).isTrue();
    }

    @Test
    void 강의_유로값_체크() {
        session.switchToFreeCourse();
        session.switchToPaidCourse();
        assertThat(session.getFree()).isFalse();
    }

    @Test
    void changeToStatus() {
        session.changeToStatus(SessionType.READY);
        assertThat(session.getStatus()).isEqualTo(SessionType.READY);

        session.changeToStatus(SessionType.RECRUITING);
        assertThat(session.getStatus()).isEqualTo(SessionType.RECRUITING);

        session.changeToStatus(SessionType.END);
        assertThat(session.getStatus()).isEqualTo(SessionType.END);
    }

    @Test
    void 강의_학생추가_테스트() {
        IntStream.range(0, 22)
                .forEach(i -> {
                    if (i == 21) {
                        assertThatThrownBy(() -> {
                            session.putStudent(new NsUser((long) i, "coby", "Car1q2w3e4r5t!","typark", "coby.typark@gmail.com", LocalDateTime.now(), LocalDateTime.now()));
                        }).isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("최대 인원을 초과하였습니다.");
                    } else {
                        session.putStudent(new NsUser((long) i, "coby", "Car1q2w3e4r5t!","typark", "coby.typark@gmail.com", LocalDateTime.now(), LocalDateTime.now()));
                    }
                });
    }

    @Test
    void 강의_모집중이_아닐때_학생추가_테스트() {
        session.changeToStatus(SessionType.READY);
        assertThatThrownBy(() -> session.putStudent(new NsUser(1L, "coby", "Car1q2w3e4r5t#","typark", "coby.typark@gmail.com", LocalDateTime.now(), LocalDateTime.now())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("모집중인 강이가 아닙니다.");

    }

    @Test
    void 강의에서_잘못된학생정보_제거() {
        assertThatThrownBy(() -> session.removeStudent(new NsUser(1L, "coby", "Ccar1q2w3e4r5t#","typark", "coby.typark@gmail.com", LocalDateTime.now(), LocalDateTime.now())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("등록된 학생 정보가 없습니다.");
    }

    @Test
    void 강의에서_학생정보_null로_제거() {
        assertThatThrownBy(() -> session.removeStudent(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("학생 데이터가 잘못 생성되었습니다.");
    }
}