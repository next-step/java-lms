package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.application.Application;
import nextstep.courses.domain.application.ApplicationRepository;
import nextstep.courses.domain.application.Applications;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@JdbcTest
public class ApplicationRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private UserRepository userRepository;

  private CourseRepository courseRepository;

  private ApplicationRepository applicationRepository;

  private Course course;

  private NsUser nsUser;

  @BeforeEach
  void setUp() {
    userRepository = new JdbcUserRepository(jdbcTemplate);
    nsUser = userRepository.findByUserId("javajigi").get();

    courseRepository = new JdbcCourseRepository(jdbcTemplate);
    Long saveId = courseRepository.save(new Course("TDD, 클린 코드 with Java", 1L));
    course = courseRepository.findById(saveId).get();

    applicationRepository = new JdbcApplicationRepository(jdbcTemplate);
  }

  @Test
  @Transactional
  void create() {
    Applications applications = new Applications(
        applicationRepository.findByCourseId(course.getId()));
    Application application = Application.createApplication(nsUser, course, applications);

    Long saveId = applicationRepository.save(application);

    Application retApplication = applicationRepository.findById(saveId).get();
    assertThat(retApplication.getNsUserId()).isEqualTo(nsUser.getId());
    assertThat(retApplication.getCourseId()).isEqualTo(course.getId());
    assertThat(retApplication.isPass()).isFalse();
  }

  @Test
  @Transactional
  void update() {
    Applications applications = new Applications(
        applicationRepository.findByCourseId(course.getId()));
    Application application = Application.createApplication(nsUser, course, applications);
    Long saveId = applicationRepository.save(application);
    Application savedApplication = applicationRepository.findById(saveId).get();
    savedApplication.applicationPass();

    Long updateId = applicationRepository.save(savedApplication);

    Application retApplication = applicationRepository.findById(updateId).get();
    assertThat(retApplication.isPass()).isTrue();
  }
}
