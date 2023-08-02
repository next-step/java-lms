package nextstep.courses.service;

import nextstep.courses.domain.application.Application;
import nextstep.courses.domain.application.ApplicationRepository;
import nextstep.courses.domain.application.Applications;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("applicationService")
public class ApplicationService {

  private CourseRepository courseRepository;
  private ApplicationRepository applicationRepository;

  public ApplicationService(CourseRepository courseRepository,
      ApplicationRepository applicationRepository) {
    this.courseRepository = courseRepository;
    this.applicationRepository = applicationRepository;
  }

  @Transactional
  public void apply(NsUser loginUser, long courseId) {
    Course course = courseRepository.findById(courseId)
        .orElseThrow(NotFoundException::new);
    Applications applications = new Applications(
        applicationRepository.findByCourseId(courseId));

    Application application = Application.createApplication(loginUser, course, applications);
    applicationRepository.save(application);
  }

  @Transactional
  public void pass(long applicationId) {
    Application application = applicationRepository.findById(applicationId)
        .orElseThrow(NotFoundException::new);
    application.applicationPass();

    applicationRepository.save(application);
  }

  @Transactional
  public void fail(long applicationId) {
    Application application = applicationRepository.findById(applicationId)
        .orElseThrow(NotFoundException::new);
    application.applicationFail();

    applicationRepository.save(application);
  }
}
