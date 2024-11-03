package nextstep.courses.domain.session;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nextstep.courses.domain.ApprovedStatus.APPROVED;
import static nextstep.courses.domain.ApprovedStatus.DENIED;
import static nextstep.courses.domain.CoverImageTest.*;
import static nextstep.courses.domain.InstructorTest.IN1;
import static nextstep.courses.domain.ProcessStatus.PROCESS;
import static nextstep.courses.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.domain.SelectedStatus.SELECTED;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StudentsTest {
    private Student[] students;
    private List<Student> studentList;
    private FreeSession freeSession;
    private Student student1;
    private Student student2;
    private Student student3;
    private long courseId;
    private DateRange dateRange;
    private List<CoverImage> coverImages;
    private CoverImage coverImage;
    private Status status;

    @BeforeEach
    void setUp() {
        courseId = CourseTest.C1.getId();
        dateRange = new DateRange(START, END);
        coverImage = new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        status = Status.PREPARE;
        coverImages = List.of(new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200));

        freeSession = new FreeSession(1L,
                courseId, dateRange,
                coverImage, status,
                coverImages, IN1, PROCESS, OPEN,
                1L, LocalDateTime.now(), LocalDateTime.now());

        student1 = new Student(freeSession, JAVAJIGI, SELECTED, DENIED, START);
        student2 = new Student(freeSession, SANJIGI, SELECTED, DENIED, START);
        student3 = new Student(freeSession, THIRDJIGI, SELECTED, DENIED, START);

        students = new Student[]{student1, student2};

        studentList = new ArrayList<>(Arrays.asList(students));
        studentList.forEach(nsUser -> freeSession.register(nsUser));
    }

    @Test
    void create() {
        Students list = new Students(studentList);
        Students array = new Students(students);

        assertThat(list).isEqualTo(array);
    }

    @Test
    void add() {
        Students actual = new Students(studentList);
        actual.add(student3);
        freeSession.register(student3);

        Students expected = new Students(
                student1,
                student2,
                student3);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void size() {
        Students students = new Students(studentList);
        students.add(student3);
        freeSession.register(student3);
        int size = students.size();

        assertThat(size).isEqualTo(3);
    }

    @Test
    void each() {
        Students actual = new Students(studentList);
        actual.each(Student::approved);
        Students expected = new Students(List.of(
                new Student(freeSession, JAVAJIGI, SELECTED, APPROVED, START),
                new Student(freeSession, SANJIGI, SELECTED, APPROVED, START)
        ));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getStudents_불변성() {
        Students students = new Students(studentList);
        List<Student> actual = students.getStudents();

        assertThat(actual).isEqualTo(studentList);
        assertThatThrownBy(() -> {
            students.getStudents().add(student3);
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}