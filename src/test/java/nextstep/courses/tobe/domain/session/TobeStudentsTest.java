package nextstep.courses.tobe.domain.session;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.domain.ProcessStatus;
import nextstep.courses.tobe.domain.RecruitmentStatus;
import nextstep.courses.tobe.domain.TobeFreeSession;
import nextstep.courses.tobe.domain.TobeStudent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.ApprovedStatus.DENIED;
import static nextstep.courses.tobe.domain.ProcessStatus.*;
import static nextstep.courses.tobe.domain.RecruitmentStatus.*;
import static nextstep.courses.tobe.domain.SelectedStatus.SELECTED;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TobeStudentsTest {
    private TobeStudent[] students;
    private List<TobeStudent> studentList;
    private TobeFreeSession freeSession;
    private TobeStudent student1;
    private TobeStudent student2;
    private TobeStudent student3;
    private long courseId;
    private DateRange dateRange;
    private List<TobeCoverImage> coverImages;

    @BeforeEach
    void setUp() {
        courseId = CourseTest.C1.getId();
        dateRange = new DateRange(START, END);
        coverImages = List.of(new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L));

        freeSession = new TobeFreeSession(1L,
                courseId, dateRange, coverImages,
                PROCESS, OPEN,
                1L, LocalDateTime.now(), LocalDateTime.now());

        student1 = new TobeStudent(freeSession, JAVAJIGI, SELECTED, DENIED, START);
        student2 = new TobeStudent(freeSession, SANJIGI, SELECTED, DENIED, START);
        student3 = new TobeStudent(freeSession, THIRDJIGI, SELECTED, DENIED, START);

        students = new TobeStudent[]{student1, student2};

        studentList = new ArrayList<>(Arrays.asList(students));
        studentList.forEach(nsUser -> freeSession.register(nsUser));
    }

    @Test
    void create() {
        TobeStudents list = new TobeStudents(studentList);
        TobeStudents array = new TobeStudents(students);

        assertThat(list).isEqualTo(array);
    }

    @Test
    void add() {
        TobeStudents actual = new TobeStudents(studentList);
        actual.add(student3);
        freeSession.register(student3);

        TobeStudents expected = new TobeStudents(
                student1,
                student2,
                student3);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void size() {
        TobeStudents students = new TobeStudents(studentList);
        students.add(student3);
        freeSession.register(student3);
        int size = students.size();

        assertThat(size).isEqualTo(3);
    }

    @Test
    void getStudents_불변성() {
        TobeStudents students = new TobeStudents(studentList);
        List<TobeStudent> actual = students.getStudents();

        assertThat(actual).isEqualTo(studentList);
        assertThatThrownBy(() -> {
            students.getStudents().add(student3);
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
