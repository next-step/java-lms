package nextstep.courses;

import nextstep.courses.service.CourseService;

public class CourseApplication {
  public static void main(String[] args) {
    CourseService courseService = new CourseService();
    courseService.start();
  }
}
