package nextstep.courses.infrastructure;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.AttendeeRepository;
import nextstep.qna.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
class JdbcAttendeeRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private AttendeeRepository attendeeRepository;

    @BeforeEach
    void setUp() {
        attendeeRepository = new JdbcAttendeeRepository(jdbcTemplate);
    }

    @DisplayName("저장하고 조회한 객체는 동일하다.")
    @Test
    void save_and_find_by_id_is_equal() {
        Attendee attendee = new Attendee(1L, 1L);
        attendeeRepository.save(attendee);
        Attendee actual = attendeeRepository.findById(1L)
                                               .orElseThrow(NotFoundException::new);
        assertThat(actual).isEqualTo(attendee);
    }
}