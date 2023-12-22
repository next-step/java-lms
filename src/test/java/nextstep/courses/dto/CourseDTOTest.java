package nextstep.courses.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Sessions;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseDTOTest {
    @Test
    @DisplayName("NsUserLimitDTO 생성")
    void create() {
        assertThat(new CourseDTO(1L, "TDD, Java with clean code", 1L, new Sessions()))
                .isInstanceOf(CourseDTO.class);
    }

    @Test
    @DisplayName("Course Id 반환")
    void getId() {
        assertThat(new CourseDTO(1L, "TDD, Java with clean code", 1L, new Sessions()).getId())
                .isOne();
    }
}
