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

import java.util.List;

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

    @DisplayName("session id 값으로 Attendee 목록을 조회한다.")
    @Test
    void find_all_by_session_id() {
        Attendee attendee1 = new Attendee(1L, 1L);
        Attendee attendee2 = new Attendee(2L, 1L);
        Attendee attendee3 = new Attendee(3L, 1L);
        attendeeRepository.save(attendee1);
        attendeeRepository.save(attendee2);
        attendeeRepository.save(attendee3);
        List<Attendee> expected = List.of(attendee1, attendee2, attendee3);

        List<Attendee> actual = attendeeRepository.findAllBySeesionId(1L);

        assertThat(actual).isEqualTo(expected);
    }
}