package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Session session;

    @BeforeEach
    void setUp() {
        LocalDateTime startedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
        LocalDateTime endedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
        boolean isFree = true;
        Status status = Status.PREPARING;
        int currentStudents = 10;
        int maxStudents = 10;
        session = new Session(startedAt, endedAt, isFree, status, currentStudents, maxStudents);
    }

    @Test
    @DisplayName("강의에 기간 설정 정상 확인")
    void term() {
        List<LocalDateTime> expected = Arrays.asList(
                LocalDateTime.parse("2022-01-01 11:11:11", formatter),
                LocalDateTime.parse("2022-01-01 11:11:11", formatter)
        );
        session.patchTerms(expected.get(0), expected.get(1));
        List<LocalDateTime> actual = session.terms();

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("강의 무료 설정 정상 확인")
    void free() {
        boolean expected = false;
        session.patchIsFree(expected);
        boolean actual = session.isFree();
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("강의 준비 중일 때 열리지 않음 확인")
    void isOpening_false() {
        Status input = Status.PREPARING;
        boolean expected = false;

        session.patchStatus(input);
        boolean actual = session.isOpening();
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("강의가 열렸을 때 열림 확인")
    void isOpening_true() {
        Status input = Status.OPENING;
        boolean expected = true;

        session.patchStatus(input);
        boolean actual = session.isOpening();
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("강의가 종료되었을 때 열리지 않음 확인")
    void enrolement_not_opning() {
        Status input = Status.ENDED;

        session.patchStatus(input);
        assertThatThrownBy(() -> session.enrolement())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("강의가 준비 중일 때 등록 불가 확인")
    void enrolement_above_max_student() {
        Status input = Status.PREPARING;

        session.patchStatus(input);
        assertThatThrownBy(() -> session.enrolement())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("강의가 열렸고 최대 수강 인원보다 현재 인원이 더 적을 시 정상 등록 확인")
    void enrolement() {
        Status inputStatus = Status.OPENING;
        int inputMaxStudents = 15;

        session.patchStatus(inputStatus);
        session.registerMaxStudents(inputMaxStudents);

        int expected = session.currentStudents() + 1;
        session.enrolement();
        int actual = session.currentStudents();
        assertThat(expected).isEqualTo(actual);
    }

}