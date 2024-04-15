package nextstep.courses;

import nextstep.courses.service.PrintExampleService;

public class CourseApplication {
  public static void main(String[] args) {
    PrintExampleService printExampleService = new PrintExampleService();
    printExampleService.start();
  }
}
