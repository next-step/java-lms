package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionTest;
import nextstep.session.domain.teacher.SessionTeachers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SessionTeacherServiceTest {

    @Autowired
    SessionTeacherService service;

    @Test
    @DisplayName(value = "강의에 강사가 없는 경우 검사")
    void test1() {
        Session session = SessionTest.s1;
        SessionTeachers teachers = service.getTeachersOfSession(session);

        assertThat(teachers.getCountOfTeachers()).isEqualTo(0);
    }
}
