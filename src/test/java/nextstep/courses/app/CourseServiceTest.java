package nextstep.courses.app;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static nextstep.fixtures.CourseFixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

@SpringBootTest

public class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @DisplayName("저장 후 조회")
    @Test
    void saveSelect() {
        courseService.save(testCourse1());

        Course findCourse = courseService.findById(1L);

        assertThat(findCourse.getId()).isEqualTo(1L);
    }
}

