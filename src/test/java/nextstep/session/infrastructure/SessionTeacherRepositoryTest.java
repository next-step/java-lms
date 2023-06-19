package nextstep.session.infrastructure;

import nextstep.session.domain.SessionTest;
import nextstep.session.domain.teacher.SessionTeacher;
import nextstep.session.domain.teacher.SessionTeacherRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionTeacherRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionTeacherRepository teacherRepository;

    @BeforeEach
    void setUp() {
        teacherRepository = new JdbcSessionTeacherRepository(jdbcTemplate);
    }


    @Test
    void crud() {
        Long id = teacherRepository.saveTeacher(SessionTest.s1.getId(), NsUserTest.JAVAJIGI.getId());
        assertThat(id).isEqualTo(1);
        teacherRepository.saveTeacher(SessionTest.s1.getId(), NsUserTest.SANJIGI.getId());

        List<SessionTeacher> teachers = teacherRepository.getTeachers(SessionTest.s1.getId());
        assertThat(teachers.size()).isEqualTo(2);
    }
}
