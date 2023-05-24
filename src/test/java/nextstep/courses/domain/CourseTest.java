package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class CourseTest {

    private Course course;
    private Session firstSession;
    private Session secondSession;
    private Session thirdSession;

    @BeforeEach
    void setUp() {
        this.course = new Course();
        this.firstSession = new Session();
        this.secondSession = new Session();
        this.thirdSession = new Session();
        addSessionToCourse();
    }

    private void addSessionToCourse() {
        course.addSession(firstSession);
        course.addSession(secondSession);
        course.addSession(thirdSession);
    }

    @DisplayName("기수에 따른 강의를 조회 할 수 있다.")
    @Test
    void name() {
        Session firstSession = course.getNThSession(1);

        assertThat(firstSession).isEqualTo(this.firstSession);
    }

    @DisplayName("기수에 따른 강의를 조회시 존재하는 기수를 조회 하면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void name3(int generation) {
        assertThatNoException()
                .isThrownBy(() -> course.getNThSession(generation));
    }

    @DisplayName("기수에 따른 강의를 조회시 음수나 0으로 조회 하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 0})
    void name1(int generation) {
        assertThatThrownBy(() -> course.getNThSession(generation))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기수에 따른 강의를 조회시 존재하는 기수를 초과하여 조회 하면 예외가 발생한다.")
    @Test
    void name2() {
        int generation = 4;

        assertThatThrownBy(() -> course.getNThSession(generation))
                .isInstanceOf(IllegalArgumentException.class);
    }
}