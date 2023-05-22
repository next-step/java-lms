package nextstep.courses.domain;

import nextstep.courses.OverNumberOfStudentsException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionStudentsTest {

    @ParameterizedTest(name = "세션의 학생 수가 증가했을 경우 정상 동작하는지 테스트")
    @CsvSource(value = {"1|1", "2|2", "3|3"}, delimiter = '|')
    void testAddStudents_학생_수_증가(int numberOfStudents, int expected) {
        SessionStudents sessionStudents = new SessionStudents();
        for (int count = 0; count < numberOfStudents; count++) {
            sessionStudents.addStudent();
        }
        assertThat(sessionStudents.getNumberOfStudents()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "세션의 학생 수가 초과되었을 경우 예외가 발생하는지 확인 테스트")
    @ValueSource(ints = {11, 12, 13})
    void testAddStudents_학생_수_초과_예외(int numberOfStudents) {
        SessionStudents sessionStudents = new SessionStudents();
        assertThatThrownBy(() -> IntStream.range(0, numberOfStudents).forEach(count -> sessionStudents.addStudent()))
                .isInstanceOf(OverNumberOfStudentsException.class)
                .hasMessage(OverNumberOfStudentsException.MESSAGE);
    }
}
