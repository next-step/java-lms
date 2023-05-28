package nextstep.courses.domain;

import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
public class CourseRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        TestFixture.fixtureInit();
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @AfterEach
    void wrapUp() {
        courseRepository.deleteAll();
    }

    @DisplayName("CRUD 전과정을 검증한다")
    @Test
    public void crud() {
        //given
        Course course1 = TestFixture.KOTLIN_COURSE;
        Course course2 = TestFixture.K8S_COURSE;
        Course course3 = TestFixture.RUST_COURSE;
        //when
        Course save1 = courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        List<Course> all = courseRepository.findAll();
        Course findById1 = courseRepository.findById(save1.getCourseId()).orElseThrow(() -> new RuntimeException("방금 저장한 데이터를 읽어옴. 성공해야한다"));
        //then
        assertAll("데이터가 잘 읽고 쓰이는지 검증한다",
                () -> assertThat(save1.getCourseId()).isEqualTo(findById1.getCourseId()),
                () -> assertThat(all.size()).isGreaterThanOrEqualTo(3)
        );
        all.stream().forEach(course -> LOGGER.info(course.toString()));
    }

    @DisplayName("저장한다")
    @Test
    void save() {
        Course course = Course.of(6L, "TDD, 클린 코드 with Java", 1L);
        Course save = courseRepository.save(course);
        assertThat(save).isNotNull();
        //Course savedCourse = courseRepository.findById(save.getCourseId().value()).orElseThrow();

        assertThat(course.getTitle()).isEqualTo(save.getTitle());
        LOGGER.debug("Course: {}", save);
    }

    @DisplayName("조회한다")
    @Test
    public void findById() {

        //todo : 조회만 돌리면 성공하는데 다른 테스트랑 전체 같이 돌리면 깨짐.. 신기하네
        //given
        Course save = courseRepository.save(TestFixture.K8S_COURSE);
        System.out.println(save);
        Course savedCourse = courseRepository.findById(save.getCourseId()).orElseThrow();

        //when
        //then
        System.out.println(savedCourse);

    }

    @DisplayName("모든 데이터를 조회한다")
    @Test
    public void findAll() {
        //given
        courseRepository.save(TestFixture.K8S_COURSE);
        courseRepository.save(TestFixture.KOTLIN_COURSE);
        courseRepository.save(TestFixture.RUST_COURSE);
        //when
        List<Course> all = courseRepository.findAll();
        //then
        for (Course course : all) {
            System.out.println(course);
        }
    }

    @DisplayName("모든 데이터를 삭제한다")
    @Test
    public void deleteAll() {
        //given
        //when
        courseRepository.deleteAll();
        //then
    }
}
