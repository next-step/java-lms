package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.lectures.Lecture;
import nextstep.courses.domain.lectures.LectureRepository;
import nextstep.courses.domain.lectures.PaidLecture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class LectureRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LectureRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private LectureRepository lectureRepository;

    @BeforeEach
    void setUp() {
        lectureRepository = new JdbcLectureRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Lecture course = new PaidLecture("TDD, 클린 코드 with Java", 1L);
        int count = lectureRepository.save(course);
        assertThat(count).isEqualTo(1);
        Lecture savedLecture = lectureRepository.findLectureById(1L);
        assertThat(savedLecture.isFree()).isEqualTo(savedCourse.getTitle());
        LOGGER.debug("Course: {}", savedCourse);
    }
}
