package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionTest;
import nextstep.session.domain.student.SessionStudent;
import nextstep.session.domain.student.SessionStudents;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionStudentServiceTest {

    @Autowired
    SessionStudentService service;

    @Test
    @DisplayName(value = "강사가 수강신청 승인")
    void test1() {
        NsUser user = NsUserTest.SANJIGI;
        Session session = new Session(SessionTest.s1, new SessionStudents(1));
        SessionStudent student = 수강신청(session, user);

        int count = service.approveStudent(student);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName(value = "강사가 수강신청 거절")
    void test2() {
        NsUser user = NsUserTest.SANJIGI;
        Session session = new Session(SessionTest.s1, new SessionStudents(1));
        SessionStudent student = 수강신청(session, user);

        int count = service.refuseStudent(student);
        assertThat(count).isEqualTo(1);
    }

    private SessionStudent 수강신청(Session session, NsUser nsUser) {
        Long id = service.enrollStudent(session, nsUser);
        return new SessionStudent(id, session.getId(), nsUser);
    }
}
