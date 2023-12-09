package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicantsTest {
    private static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    private static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    private static final NsUser APPLE = new NsUser(3L, "sanjigi", "password", "name", "apple@slipp.net");

    Applicants applicants;
    private int qouta;

    @BeforeEach
    void setUp() {
        qouta = 2;
        applicants = new Applicants(qouta);
        applicants.addChargedApplicant(JAVAJIGI);
    }

    @Test
    @DisplayName("addApplicant 는 이미 수강생이 강의를 신청 하였으면 예외를 던진다.")
    void addApplicant_alreadyExistedApplicant_throwsException() {
        assertThatThrownBy(
                () -> applicants.addChargedApplicant(JAVAJIGI)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("addApplicant 는 유료강의 수강 정원이 찼으면 예외를 던진다.")
    void addApplicant_alreadyFull_throwsException() {
        applicants = new Applicants(2);
        applicants.addChargedApplicant(SANJIGI);
        applicants.addChargedApplicant(APPLE);

        assertThatThrownBy(
                () -> applicants.addChargedApplicant(SANJIGI)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("isFull 은 수강생 수와 정원이 같으면 true를 적으면 false를 반환한다.")
    void isFull_sameOrOverQuota_throwsException() {
        applicants = new Applicants(2);
        applicants.addFreeApplicant(SANJIGI);

        assertThat(applicants.isFull()).isFalse();

        applicants.addFreeApplicant(JAVAJIGI);
        assertThat(applicants.isFull()).isTrue();
    }
}
