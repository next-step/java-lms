package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import nextstep.sessions.testFixture.SessionBuilder;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class StudentsTest {

  @Test
  void overfull_false() {
    int capacity = 5;
    Session session = SessionBuilder.aSession().withCapacity(capacity).build();
    Student student1 = new Student(session, NsUserTest.JAVAJIGI, LocalDateTime.now(), null);
    Student student2 = new Student(session, NsUserTest.SANJIGI, LocalDateTime.now(), null);
    Students students = new Students(Set.of(student1, student2));

    assertThat(students.overFull(capacity)).isFalse();
  }

  @Test
  void overfull_true() {
    int capacity = 1;
    Session session = SessionBuilder.aSession().withCapacity(capacity).build();
    Student student1 = new Student(session, NsUserTest.JAVAJIGI, LocalDateTime.now(), null);
    Student student2 = new Student(session, NsUserTest.SANJIGI, LocalDateTime.now(), null);
    Students students = new Students(Set.of(student1, student2));

    assertThat(students.overFull(capacity)).isTrue();
  }

  @Test
  void contains_true() {
    Session session = SessionBuilder.aSession().build();
    Student student = new Student(session, NsUserTest.JAVAJIGI, LocalDateTime.now(), null);
    Students students = new Students(Set.of(student));

    assertThat(students.contains(student)).isTrue();
  }

  @Test
  void contains_false() {
    Session session = SessionBuilder.aSession().build();
    Student student = new Student(session, NsUserTest.JAVAJIGI, LocalDateTime.now(), null);
    Students students = new Students(new HashSet<>());

    assertThat(students.contains(student)).isFalse();
  }
}