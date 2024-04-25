package nextstep.courses.domain;

import nextstep.courses.domain.image.Images;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionApplyStatus;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.Student;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionTest {
    private final Long sessionId = 1L;
    private final LocalDate startDate = LocalDate.now();
    private final LocalDate endDate = LocalDate.now();
    private final Images coverImages = new Images(new ArrayList<>());
    private final int fee = 10000;
    private final int maxStudents = 1;

    private Session session;
    private NsUser loginUser;

    @BeforeEach
    public void setUp() {
        session = new Session(sessionId, startDate, endDate, coverImages, fee, maxStudents);
        session.changeProgressStatus(SessionProgressStatus.IN_PROGRESS);
        session.changeApplyStatus(SessionApplyStatus.APPLYING);

        loginUser = new NsUser();
    }

    @Test
    public void 무료강의는_최대수강인원_제한이_없다() {
        Session freeSession = new Session(sessionId, startDate, endDate, coverImages, 0, 1);

        freeSession.changeProgressStatus(SessionProgressStatus.IN_PROGRESS);
        freeSession.changeApplyStatus(SessionApplyStatus.APPLYING);

        List<Student> studentList = createStudentList(2, 0);
        assertDoesNotThrow(() -> {
            for(Student student : studentList) {
                freeSession.enroll(student);
            }
        });
    }

    @Test
    public void 강의는_강의최대수강인원을_초과할수없다() {
        List<Student> studentList = createStudentList(2, 10000);
        assertThrows(RuntimeException.class, () -> {
            for(Student student : studentList) {
                session.enroll(student);
            }
        });
    }

    @Test
    public void 강의는_수강생이_결제한금액과_수강료가_일치할때_수강신청이_가능하다() {
        assertDoesNotThrow(() -> {
            session.enroll(createCanApplyStudent(10000));
        });
    }

    @Test
    public void 강의는_수강생이_결제한금액과_수강료가_일치하지않으면_수강신청이_불가능하다() {
        assertThrows(RuntimeException.class, () -> session.enroll(createCanApplyStudent(5000)));
    }

    @Test
    public void 강의_수강신청은_강의진행상태가_준비중_또는_진행중이고_강의모집상태가_모집중일때만_가능하다() {
        assertDoesNotThrow(() -> session.enroll(createCanApplyStudent(10000)));
    }

    @Test
    public void 강의_수강신청은_강의_상태가_모집중이_아니면_불가능하다() {
        session.changeApplyStatus(SessionApplyStatus.NOT_APPLYING);

        assertThrows(RuntimeException.class, () -> session.enroll(createCanApplyStudent(10000)));
    }

    @Test
    public void 강사는_수강신청한_사람중_선발된_인원에_대해서만_수강승인이_가능해야한다() {
        Student student = createSelectedStudent();
        student.pay(fee);

        assertDoesNotThrow(() -> session.enroll(student));
        assertThat(session.getStudentsCount()).isEqualTo(1);
    }

    @Test
    public void 강사는_수강신청한_사람중_선발되지_않은_사람은_수강을_취소할수있어야_한다() {
        Student student = createStudent();
        student.pay(fee);

        assertThrows(RuntimeException.class, () -> session.enroll(student));
        assertThat(session.getStudentsCount()).isEqualTo(0);
    }

    private List<Student> createStudentList(int number, int fee) {
        List<Student> studentList = new ArrayList<>();

        for (int i=0; i<number; i++) {
            studentList.add(createCanApplyStudent(fee));
        }

        return studentList;
    }

    private Student createCanApplyStudent(int fee) {
        Student student = createSelectedStudent();
        student.pay(fee);

        return student;
    }

    private Student createSelectedStudent() {
        Student student = createStudent();
        student.select();

        return student;
    }

    private Student createStudent() {
        return new Student(session.getId(), loginUser.getId());
    }
}
