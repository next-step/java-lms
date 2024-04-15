package nextstep.courses.domain;

import nextstep.courses.exception.SessionAlreadyExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CourseTest {

    public static final Course C1 = new Course("course title1", 0L);

    @Test
    @DisplayName("기수 실패 테스트 - 중복된 강의")
    void courseFailForSessionAlreadyExistTest() throws Exception {
        C1.addSession(SessionTest.FREE_SESSION);

        assertThatThrownBy(() -> {
                    C1.addSession(SessionTest.FREE_SESSION);
                }).isInstanceOf(SessionAlreadyExistException.class)
                .hasMessage("중복된 강의가 있습니다.");
    }
}
