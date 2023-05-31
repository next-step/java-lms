package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseBuilder;
import nextstep.sessions.domain.SessionBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    @DisplayName("코스 저장 및 조회")
    void test01() {
        String expectedTitle = "테스트";
        long expectedCreatorId = 123L;
        courseService.save(
                CourseBuilder.aCourse()
                        .withTitle(expectedTitle)
                        .withCreatorId(expectedCreatorId)
                        .withCreatedAt(LocalDateTime.now())
                        .with(SessionBuilder.aSession())
                        .build());
        Course savedCourse = courseService.findById(1L);

        assertAll(
                () -> assertThat(savedCourse.getTitle()).isEqualTo(expectedTitle),
                () -> assertThat(savedCourse.getCreatorId()).isEqualTo(expectedCreatorId)
        );
    }

}
