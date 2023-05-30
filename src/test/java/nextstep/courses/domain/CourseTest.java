package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CourseTest {

    @Test
    @DisplayName("Course_생성_테스트")
    public void Course_생성_테스트(){
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(new Session(SessionStatus.READY));
        sessionList.add(new Session(SessionStatus.RECRUIT));
//        assertThat(new Course(sessionList)).isEqualTo(new Course(sessionList));
    }
}
