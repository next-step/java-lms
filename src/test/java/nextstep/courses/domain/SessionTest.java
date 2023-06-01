package nextstep.courses.domain;

import nextstep.users.domain.NsStudent;
import nextstep.users.domain.NsTeacher;
import nextstep.users.domain.StudentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    public NsStudent JAVAJIGI;
    public NsStudent SANJIGI ;
    public NsTeacher teacher ;
    public Session session;


    @BeforeEach
    void setup(){
        JAVAJIGI = new NsStudent(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        SANJIGI = new NsStudent(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        teacher = new NsTeacher(3L, "teacher", "password", "name", "sanjigi@slipp.net");
        session = new Session(1L, teacher);
    }

    @Test
    @DisplayName("Validate_date_test")
    public void Validate_date_test() {
        LocalDate startedAt = LocalDate.of(2022, 11, 10);
        LocalDate endedAt = LocalDate.of(2021, 11, 10);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    new Session(startedAt, endedAt, 1L);
                });
    }

    @Test
    @DisplayName("register_method_test")
    public void register_method_test() {
        Session session = new Session(SessionEnrollment.ENROLLMENT);
        session.register(JAVAJIGI);

        assertThat(session.getNumberOfRegisteredStudent()).isEqualTo(1);
    }

    @Test
    @DisplayName("validate_capacityStudent_test")
    public void validate_capacityStudent_test() {
        Session session = new Session(2);
        session.register(JAVAJIGI);
        session.register(SANJIGI);

        assertThatIllegalStateException()
                .isThrownBy(() -> session.register(SANJIGI));
    }

    @Test
    @DisplayName("validate_Status_test")
    public void validate_Status_test() {
        Session session = new Session(SessionEnrollment.NON_ENROLLMENT);
        assertThatIllegalStateException()
                .isThrownBy(() -> session.register(SANJIGI));
    }

    @Test
    public void approve(){
        session.register(SANJIGI);
        SANJIGI.register(1L);
        teacher.chargeSession(1L);
        session.approve(teacher, SANJIGI);

        assertThat(SANJIGI.sessionEnrollment().get(1L)).isEqualTo(StudentStatus.APPROVE);
    }

    @Test
    public void refuse(){
        session.register(JAVAJIGI);
        SANJIGI.register(1L);
        teacher.chargeSession(1L);
        session.refuse(teacher, JAVAJIGI);

        assertThat(JAVAJIGI.sessionEnrollment().get(1L)).isEqualTo(StudentStatus.REFUSE);
    }

    @Test
    public void validate_with_teacher(){
        session.register(SANJIGI);
        SANJIGI.register(1L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.approve(teacher, SANJIGI))
                .withMessage("담당 강사가 아닙니다.");
    }

    @Test
    public void validate_with_student(){
        teacher.chargeSession(1L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.approve(teacher, SANJIGI))
                .withMessage("신청한 학생이 아닙니다.");
    }


}
