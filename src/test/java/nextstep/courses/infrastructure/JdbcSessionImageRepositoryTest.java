package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.enums.SessionProcessStatus;
import nextstep.courses.domain.session.enums.SessionRecruitStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class JdbcSessionImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcSessionImageRepository sessionImageRepository;

    @BeforeEach
    void setUp() {
        sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
    }

    @Test
    void save() throws PeriodException {
        SessionImage sessionImage = new SessionImage(1024, "jpg", new ImageSize(300, 200));
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
        Session session = new Session(6L, period, SessionRecruitStatus.OPEN, SessionProcessStatus.WAITING, new Students(), new SessionType(), List.of(sessionImage));
        sessionImage.addSession(session);
        int count = sessionImageRepository.save(sessionImage);
        assertThat(count).isEqualTo(1);
        SessionImage save = sessionImageRepository.findById(1L);
        assertThat(save.getFileSize()).isEqualTo(sessionImage.getFileSize());
    }
}