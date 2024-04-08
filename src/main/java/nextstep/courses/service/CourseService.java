package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.Generation;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.session.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class CourseService {

  public void start() {
    Course course = generateTddJavaCourse();
    addSessionIn(course);

    System.out.println("Course 명: " + course.getTitle());
    printSessionInfos(course);

    System.out.println();
    System.out.println("==========================");
    System.out.println("        수강신청 중        ");
    System.out.println("==========================");
    System.out.println();

    doRegister(course);

    printSessionInfos(course);
  }

  private static Course generateTddJavaCourse() {
    return new Course(1L, new Generation(1L), "TDD, 클린 코드 with Java", 100L, LocalDateTime.now(), LocalDateTime.now());
  }

  private static void addSessionIn(Course course) {
    Session session1 = new Session(
        1L,
        new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
        new SessionInfo("자동차 경주", SessionStatus.IN_PROGRESS, true, 0L, null),
        new CoverImage("자동차 경주 이미지 커버", ImageType.PNG, new CoverImageMeta(1024, 300, 200))
    );

    Session session2 = new Session(
        2L,
        new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
        new SessionInfo("레거시 코드 리팩토링", SessionStatus.IN_PROGRESS, false, 50000L, 100),
        new CoverImage("리팩토링 이미지 커버", ImageType.PNG, new CoverImageMeta(1024, 300, 200))
    );

    course.addSessions(Set.of(session1, session2));
  }

  private static void printSessionInfos(Course course) {
    for (Session session : course.getSessions()) {

      String printAmount = session.getSessionAmount() == 0 ? "무료" : session.getSessionAmount().toString() + "원";

      System.out.println("  ㄴ 강의 명: " + session.getSessionTitle() + ", 강의 상태: " + session.getSessionStatus());
      System.out.println("    ㄴ 강의 금액: " + printAmount);

      if (session.isFree()) {
        System.out.println("    ㄴ 수강 인원: " + session.getTotalStudentCount() + "명");
      }

      if (!session.isFree()) {
        System.out.println("    ㄴ 수강 인원: " + session.getTotalStudentCount() + "명 / 최대 수강 인원: " + session.getStudentMaxCount() + "명");
      }
    }
  }

  private static void doRegister(Course course) {
    Optional<Session> freeSession = course.getSessions().stream().filter(it -> it.isFree() && it.checkRegisterPossibleStatus()).findFirst();
    Optional<Session> notFreeSession = course.getSessions().stream().filter(it -> !it.isFree() && it.checkRegisterPossibleStatus()).findFirst();
    Enrollment.register(1L, 1004L, course.getId(), freeSession.get(), 0L);
    Enrollment.register(2L, 1005L, course.getId(), notFreeSession.get(), notFreeSession.get().getSessionAmount());
    Enrollment.register(3L, 1006L, course.getId(), notFreeSession.get(), notFreeSession.get().getSessionAmount());
  }
}
