package nextstep.courses.domain;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.Student;
import nextstep.courses.domain.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionTest {
    private final LocalDate startDate = LocalDate.now();
    private final LocalDate endDate = LocalDate.now();
    private final Image coverImage = new Image(1024, "gif", 300, 200);
    private final int fee = 10000;
    private final int maxStudents = 1;

    private Session session;

    @BeforeEach
    public void setUp() {
        session = new Session(startDate, endDate, coverImage, fee, maxStudents);
    }

    @Test
    public void 무료강의는_최대수강인원_제한이_없다() {
        Session freeSession = new Session(startDate, endDate, coverImage, 0, 1);

        freeSession.changeStatus(SessionStatus.APPLYING);

        List<Student> studentList = createStudentList(2, 0);
        assertDoesNotThrow(() -> {
            for(Student student : studentList) {
                freeSession.enrollment().enroll(student);
            }
        });
    }

    @Test
    public void 강의는_강의최대수강인원을_초과할수없다() {
        session.changeStatus(SessionStatus.APPLYING);

        List<Student> studentList = createStudentList(2, 10000);
        assertThrows(RuntimeException.class, () -> {
            for(Student student : studentList) {
                session.enrollment().enroll(student);
            }
        });
    }

    @Test
    public void 강의는_수강생이_결제한금액과_수강료가_일치할때_수강신청이_가능하다() {
        Session Session = new Session(startDate, endDate, coverImage, fee, maxStudents);
        Session.changeStatus(SessionStatus.APPLYING);

        Student student = new Student(10000);
        assertDoesNotThrow(() -> {
            Session.enrollment().enroll(student);
        });
    }

    @Test
    public void 강의는_수강생이_결제한금액과_수강료가_일치하지않으면_수강신청이_불가능하다() {
        Session Session = new Session(startDate, endDate, coverImage, fee, maxStudents);

        Session.changeStatus(SessionStatus.APPLYING);

        Student student = new Student(5000);
        assertThrows(RuntimeException.class, () -> Session.enrollment().enroll(student));
    }

    @Test
    public void 강의_수강신청은_강의_상태가_모집중일때만_가능하다() {
        Session Session = new Session(startDate, endDate, coverImage, fee, maxStudents);

        Session.changeStatus(SessionStatus.APPLYING);

        Student student = new Student(10000);
        assertDoesNotThrow(() -> Session.enrollment().enroll(student));
    }

    @Test
    public void 강의_수강신청은_강의_상태가_모집중이_아니면_불가능하다() {
        Session Session = new Session(startDate, endDate, coverImage, fee, maxStudents);

        Session.changeStatus(SessionStatus.COMPLETE);

        Student student = new Student(10000);
        assertThrows(RuntimeException.class, () -> Session.enrollment().enroll(student));
    }

    private List<Student> createStudentList(int number, int fee) {
        List<Student> studentList = new ArrayList<>();

        for (int i=0; i<number; i++) {
            Student student = new Student(fee);
            studentList.add(student);
        }

        return studentList;
    }
}
