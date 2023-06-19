package nextstep.session.infrastructure;

import nextstep.session.domain.*;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.student.SessionStudentRepository;
import nextstep.session.domain.student.SessionStudentStatus;
import nextstep.session.domain.student.SessionStudents;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.session.domain.SessionCoverImageTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private Session session;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        session = new Session(SessionTest.s1, new SessionStudents(1));
    }

    @Test
    void save_and_findById() {
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L).orElseThrow();
        assertThat(savedSession.getCourseId()).isEqualTo(session.getCourseId());
    }

    @Test
    void save_and_find_image() {
        int count = sessionRepository.saveCoverImage(1L, SessionCoverImageTest.image1);
        assertThat(count).isEqualTo(1);

        SessionCoverImage sessionCoverImage = sessionRepository.findCoverImageBySessionId(1L).orElseThrow();

        assertThat(sessionCoverImage.getUrl()).isEqualTo(SessionCoverImageTest.image1.getUrl());
        assertThat(sessionCoverImage.getImageName()).isEqualTo(SessionCoverImageTest.image1.getImageName());
        assertThat(sessionCoverImage.getImageType()).isEqualTo(SessionCoverImageTest.image1.getImageType());
    }
}
