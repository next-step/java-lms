package nextstep.courses.domain.session;

import nextstep.courses.domain.session.builder.EnrollmentBuilder;
import nextstep.courses.domain.session.constant.EnrollmentStatus;
import nextstep.courses.domain.session.constant.SelectionStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class EnrollmentTest {

    @Test
    void 수강신청_정상_생성() {
        Enrollment enrollment = new Enrollment(NsUserTest.JAVAJIGI, 1L, LocalDateTime.now(), null);
        assertThat(enrollment).isNotNull();
    }

    @Test
    void 선발된인원_수강신청_승인(){
        Enrollment enrollment = new EnrollmentBuilder().withSelectionStatus(SelectionStatus.from("선발")).build();
        enrollment.updateEnrollmentStatus(EnrollmentStatus.from("승인"));
        assertThat(enrollment.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.from("승인"));
    }

    @Test
    void 미선발인원_승인시_에러발생(){
        Enrollment enrollment = new EnrollmentBuilder().withSelectionStatus(SelectionStatus.from("미선발")).build();
        assertThatThrownBy(() -> enrollment.updateEnrollmentStatus(EnrollmentStatus.from("승인")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 미선발인원_수강취소(){
        Enrollment enrollment = new EnrollmentBuilder().withSelectionStatus(SelectionStatus.from("미선발")).build();
        enrollment.updateEnrollmentStatus(EnrollmentStatus.from("취소"));
        assertThat(enrollment.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.from("취소"));
    }

    @Test
    void 선발인원_수강취소_에러발생(){
        Enrollment enrollment = new EnrollmentBuilder().withSelectionStatus(SelectionStatus.from("선발")).build();
        assertThatThrownBy(() -> enrollment.updateEnrollmentStatus(EnrollmentStatus.from("취소")))
                .isInstanceOf(IllegalArgumentException.class);
    }

}