package nextstep.courses.domain.session;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Student;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StudentsTest {

    private Student[] students;
    private List<Student> studentList;
    private FreeSession freeSession;

    @BeforeEach
    void setUp() {
        freeSession = new FreeSession(1L,
                1L,
                CourseTest.C1.getId(),
                new DateRange(START, END),
                new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT),
                Status.PREPARE,
                LocalDateTime.now(),
                LocalDateTime.now());
        students = new Student[]{new Student(freeSession, NsUserTest.JAVAJIGI, START), new Student(freeSession, NsUserTest.SANJIGI, START)};
        studentList = new ArrayList<>(Arrays.asList(students));
    }

    @Test
    void create() {
        Students list = new Students(studentList);
        studentList.forEach(student -> freeSession.register(student));
        studentList.add(new Student(freeSession, NsUserTest.THIRDJIGI, START)); // 불변 테스트
        Students array = new Students(students);

        assertThat(list).isEqualTo(array);
    }

    @Test
    void add() {
        Students students = new Students();
        studentList.forEach(student -> {
            freeSession.register(student);
            students.add(student);
        });
        freeSession.register(new Student(freeSession, NsUserTest.THIRDJIGI, START));
        students.add(new Student(freeSession, NsUserTest.THIRDJIGI, START));

        Students expected = new Students(
                new Student(freeSession, NsUserTest.JAVAJIGI, START),
                new Student(freeSession, NsUserTest.SANJIGI, START),
                new Student(freeSession, NsUserTest.THIRDJIGI, START));

        assertThat(students).isEqualTo(expected);
    }

    @Test
    void size() {
        Students students = new Students(studentList);
        studentList.forEach(nsUser -> freeSession.register(nsUser));
        freeSession.register(new Student(freeSession, NsUserTest.THIRDJIGI, START));
        students.add(new Student(freeSession, NsUserTest.THIRDJIGI, START));
        int size = students.size();

        assertThat(size).isEqualTo(3);
    }

    @Test
    void getStudents_불변성() {
        Students students = new Students(studentList);
        List<Student> actual = students.getStudents();

        assertThat(actual).isEqualTo(studentList);
        assertThatThrownBy(() -> {
            students.getStudents().add(new Student(freeSession, NsUserTest.THIRDJIGI, START));
        }).isInstanceOf(UnsupportedOperationException.class);
    }

}