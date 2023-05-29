package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentException;
import nextstep.courses.exception.SignUpFullException;
import nextstep.courses.infrastructure.JdbcCourseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionStatusTest {
    @Test
    void 생성자테스트() {
        Assertions.assertThat(new SessionStatus(30)).isInstanceOf(SessionStatus.class);
    }

    @Test
    void 중복등록불가테스트() {
        SessionStatus sessionStatus = new SessionStatus(30);

        sessionStatus.signUp(new Student("jerry", "제리", "jerry@gamil.com"));

        assertThatThrownBy(() -> {
            sessionStatus.signUp(new Student("jerry", "제리", "jerry@gamil.com"));
        }).isInstanceOf(DuplicateStudentException.class).hasMessageContaining("이미 등록된 학생ID입니다.");
    }

    @Test
    void 최대인원초과테스트() {
        SessionStatus sessionStatus = new SessionStatus(1);

        sessionStatus.signUp(new Student("jerry", "제리", "jerry@gamil.com"));

        assertThatThrownBy(() -> {
            sessionStatus.signUp(new Student("david", "데이빗", "david@gamil.com"));
        }).isInstanceOf(SignUpFullException.class).hasMessageContaining("최대 수강 인원을 초과하여 신청이 불가합니다.");
    }
}
