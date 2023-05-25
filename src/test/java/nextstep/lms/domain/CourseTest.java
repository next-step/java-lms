package nextstep.lms.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CourseTest {

    @Test
    @DisplayName("Course_생성_테스트")
    public void Course_생성_테스트(){
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(new Session(Status.READY));
        sessionList.add(new Session(Status.RECRUIT));
        assertThat(new Course(sessionList)).isEqualTo(new Course(sessionList));
    }
}
