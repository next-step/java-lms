package nextstep.users.domain;

import nextstep.courses.domain.session.EnrollmentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "test", "자바지기", "javajigi@slipp.net");
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "test", "산지기", "sanjigi@slipp.net");
    public static final NsUser POBIJIGI = new NsUser(3L, "pobijigi", "test", "포비지기", "pobijigi@slipp.net");



    @DisplayName("사용자의 수강신청 승인 여부를 알 수 있다.")
    @Test
    void isApprovedTest() {
        NsUser approvedUser = new NsUser(3L, "pobijigi", "test", "포비지기", "pobijigi@slipp.net", EnrollmentStatus.APPROVED, LocalDateTime.now(), LocalDateTime.now());
        NsUser rejectedUser = new NsUser(3L, "pobijigi", "test", "포비지기", "pobijigi@slipp.net", EnrollmentStatus.REJECTED, LocalDateTime.now(), LocalDateTime.now());

        assertThat(approvedUser.isApproved()).isTrue();
        assertThat(rejectedUser.isApproved()).isFalse();
    }


    @DisplayName("사용자의 수강신청 취소 여부를 알 수 있다.")
    @Test
    void isRejectedTest() {
        NsUser approvedUser = new NsUser(1L, "javajigi", "test", "자바지기", "javajigi@slipp.net", EnrollmentStatus.APPROVED, LocalDateTime.now(), LocalDateTime.now());
        NsUser rejectedUser = new NsUser(3L, "pobijigi", "test", "포비지기", "pobijigi@slipp.net", EnrollmentStatus.REJECTED, LocalDateTime.now(), LocalDateTime.now());

        assertThat(rejectedUser.isRejected()).isTrue();
        assertThat(approvedUser.isRejected()).isFalse();
    }
}
