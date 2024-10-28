package nextstep.courses.domain.session;

import nextstep.courses.EndBeforeStartException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.DateRange.END_BEFORE_START_MESSAGE;

public class DateRangeTest {

    public static final LocalDateTime START = LocalDateTime.of(2024, 10, 26, 10, 0);
    public static final LocalDateTime END = LocalDateTime.of(2024, 11, 26, 10, 0);
    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    void setUp() {
        start = START;
        end = END;
    }

    @Test
    void create() {
        Assertions.assertThatNoException().isThrownBy(() -> {
            DateRange dateRange = new DateRange(start, end);
        });
    }

    @Test
    void create_종료일이_시작일보다_과거일수_없다() {
        Assertions.assertThatThrownBy(() -> {
            LocalDateTime temp = start;
            LocalDateTime endBeforeStart = end;
            LocalDateTime startAfterEnd = start;
            DateRange dateRange = new DateRange(endBeforeStart, startAfterEnd);
        })
                .isInstanceOf(EndBeforeStartException.class)
                .hasMessage(END_BEFORE_START_MESSAGE);
    }
}