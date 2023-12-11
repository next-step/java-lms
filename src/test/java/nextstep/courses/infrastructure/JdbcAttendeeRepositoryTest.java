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

import static nextstep.courses.domain.attendee.Approval.*;
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
        Attendee attendee = new Attendee(1L, 1L, NOT_APPROVED);
        attendeeRepository.save(attendee);

        Attendee actual = attendeeRepository.findById(1L)
                                            .orElseThrow(NotFoundException::new);

        assertThat(actual).isEqualTo(attendee);
    }

    @DisplayName("session id 값으로 Attendee 목록을 조회한다.")
    @Test
    void find_all_by_session_id() {
        Attendee attendee1 = new Attendee(1L, 1L, NOT_APPROVED);
        Attendee attendee2 = new Attendee(2L, 1L, NOT_APPROVED);
        Attendee attendee3 = new Attendee(3L, 1L, NOT_APPROVED);
        attendeeRepository.save(attendee1);
        attendeeRepository.save(attendee2);
        attendeeRepository.save(attendee3);
        List<Attendee> expected = List.of(attendee1, attendee2, attendee3);

        List<Attendee> actual = attendeeRepository.findAllBySessionId(1L);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("승인처리 후 수강생을 저장한다.")
    @Test
    void approve_attendee_and_update() {
        Attendee attendee = new Attendee(10L, 1L, NOT_APPROVED);
        Attendee approved = attendee.approve();
        attendeeRepository.save(approved);

        Attendee actual = attendeeRepository.findById(1L)
                                            .orElseThrow(NotFoundException::new);

        assertThat(actual).isEqualTo(approved);
    }

    @DisplayName("취소 처리 후 수강생을 저장한다.")
    @Test
    void cancel_attendee_and_update() {
        Attendee attendee = new Attendee(2L, 1L, APPROVED);
        Attendee canceled = attendee.cancel();
        attendeeRepository.save(canceled);

        Attendee actual = attendeeRepository.findById(1L)
                                            .orElseThrow(NotFoundException::new);

        assertThat(actual).isEqualTo(canceled);
    }
}