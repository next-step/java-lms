package nextstep.courses.domain.course;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseTest {

    @DisplayName("제목과 작성자 아이디, 기수 번호 로 과정을 만들 수 있다.")
    @Test
    void courseCreateTest() {
        String title = "TDD, 클린 코드 with Java";
        long creatorId = 1L;
        Integer classNumber = 1;
        Course course = new Course(title, classNumber, creatorId);

        assertThat(course.getTitle()).isEqualTo(title);
        assertThat(course.getClassNumber()).isEqualTo(classNumber);
        assertThat(course.getCreatorId()).isEqualTo(creatorId);
    }

    @Test
    @DisplayName("title이 없으면 예외가 발생한다.")
    void throwExceptionWhenTitleIsNull() {
        assertThatThrownBy(() -> new Course(null, 1, 100L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title은 필수 입력 사항입니다.");
    }

    @Test
    @DisplayName("creatorId가 없으면 예외가 발생한다.")
    void throwExceptionWhenCreatorIdIsNull() {
        assertThatThrownBy(() -> new Course("Java Programming", 1, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CreatorId는 필수 입력 사항입니다.");
    }

    @Test
    @DisplayName("classNumber가 없으면예외가 발생한다.")
    void throwExceptionWhenClassNumberIsNull() {
        assertThatThrownBy(() -> new Course("Java Programming", null, 100L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ClassNumber는 필수 입력 사항입니다.");
    }

}
