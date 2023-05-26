package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
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
        session = new Session();
    }

    @Test
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
    void free() {
        boolean expected = false;
        session.patchIsFree(expected);
        boolean actual = session.isFree();
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void statusError() {
        String input = "sss";

        assertThatThrownBy(() -> session.patchStatus(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isOpening_false() {
        String input = "preparing";
        boolean expected = false;

        session.patchStatus(input);
        boolean actual = session.isOpening();
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void isOpening_true() {
        String input = "opening";
        boolean expected = true;

        session.patchStatus(input);
        boolean actual = session.isOpening();
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void enrolement_not_opning() {
        String input = "ended";

        session.patchStatus(input);
        assertThatThrownBy(() -> session.enrolement())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void enrolement_above_max_student() {
        String input = "preparing";

        session.patchStatus(input);
        assertThatThrownBy(() -> session.enrolement())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void enrolement() {
        String inputStatus = "preparing";
        int inputMaxStudents = 5;

        session.patchStatus(inputStatus);
        session.registerMaxStudents(inputMaxStudents);

        int expected = 1;
        session.enrolement();
        int actual = session.currentStudents();
        assertThat(expected).isEqualTo(actual);
    }

}