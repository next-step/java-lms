package nextstep.courses.domian;

import nextstep.courses.domain.SessionStuden;
import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStudenTest {

    @Test
    @DisplayName("최대 수강생의 수치에 미치지 못할경우 false를 반환한다.")
    void isMaxStudents_not_full() {
        SessionStuden sessionStuden = new SessionStuden(300);

        assertThat(sessionStuden.isMaxStudents()).isFalse();
    }

    @Test
    @DisplayName("최대 수갱생의 수치가 모두 찼을경우 true를 반환한다.")
    void isMaxStudents_full() {
        SessionStuden sessionStuden = new SessionStuden(new Students(List.of(NsUserTest.JAVAJIGI)), 1);

        assertThat(sessionStuden.isMaxStudents()).isTrue();
    }
}
