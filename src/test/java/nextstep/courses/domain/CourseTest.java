package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void term() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);

        List<LocalDateTime> expected = Arrays.asList(
                LocalDateTime.parse("2022-01-01 11:11:11", formatter),
                LocalDateTime.parse("2022-01-01 11:11:11", formatter)
        );
        course.putTerms(expected.get(0), expected.get(1));
        List<LocalDateTime> actual = course.terms();

        assertThat(expected).isEqualTo(actual);
    }

}