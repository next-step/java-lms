package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FreeSessionTest {

    private SessionBody sessionBody;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private CoverImage coverImage;

    @BeforeEach
    void setUp() {
        String title = "자바의 정석";
        startDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        endDate = LocalDateTime.of(2024, 1, 10, 18, 0);
        ImageSize imageSize = ImageSize.of(500 * 1024);
        ImageDimension imageDimension = ImageDimension.of(300, 200);

        coverImage = CoverImage.of("learning java", imageSize, "jpg", imageDimension);
        sessionBody = SessionBody.of(title, SessionPeriod.of(startDate, endDate), List.of(coverImage));
    }

    @Test
    @DisplayName("무료 강의 생성 시 필드가 올바르게 설정되는지 확인")
    void createFreeSessionTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.RECRUITING);

        FreeSession freeSession = new FreeSession(1L, 1L, sessionBody, sessionEnrollment);
        CoverImage retrievedCoverImage = SessionDomainTestHelper.getSingleCoverImage(freeSession.getCoverImages());

        assertAll(
                () -> assertThat("자바의 정석").isEqualTo(freeSession.getTitle()),
                () -> assertThat(startDate).isEqualTo(freeSession.getPeriod().getStartDate()),
                () -> assertThat(endDate).isEqualTo(freeSession.getPeriod().getEndDate()),
                () -> assertThat(300).isEqualTo(retrievedCoverImage.getWidth()),
                () -> assertThat(200).isEqualTo(retrievedCoverImage.getHeight())
        );
    }

    @Test
    @DisplayName("무료 강의는 모집중 또는 진행중인 상태일 때만 수강 신청 가능하다.")
    void freeSessionEnrollTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.RECRUITING);
        FreeSession freeSession = new FreeSession(1L, 1L, sessionBody, sessionEnrollment);
        Student student = Student.of(NsUserTest.SANJIGI.getId(), freeSession.getId());

        assertThatCode(() -> freeSession.enroll(student, null))
                .doesNotThrowAnyException();

        assertThat(freeSession.getEnrolledStudents()).contains(student);
    }

    @Test
    @DisplayName("무료 강의는 진행중 또는 모집중 상태가 아니면 수강 신청 시 예외가 발생한다.")
    void throwExceptionWhenStatusIsNotInProgress() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.CLOSED, RecruitmentStatus.NOT_RECRUITING);
        FreeSession freeSession = new FreeSession(1L, 1L, sessionBody, sessionEnrollment);
        Student student = Student.of(NsUserTest.JAVAJIGI.getId(), freeSession.getId());

        assertThatThrownBy(() -> freeSession.enroll(student, null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("진행중 또는 모집중인 상태에서만 신청 가능합니다.");
    }

    @DisplayName("무료 강의에 수강신청된 사용자의 수강을 승인합니다.")
    @Test
    void freeSessionApproveUserTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.PREPARE, RecruitmentStatus.RECRUITING);
        FreeSession freeSession = new FreeSession(1L, 1L, sessionBody, sessionEnrollment);
        Student student = Student.of(NsUserTest.SANJIGI.getId(), freeSession.getId());

        freeSession.enroll(student, null);
        freeSession.approve(student);

        Student approvedStudent = SessionDomainTestHelper.getSingleStudent(freeSession);

        assertThat(approvedStudent.isApproved()).isTrue();
    }

    @DisplayName("무료 강의에 수강신청된 사용자의 수강을 취소합니다.")
    @Test
    void freeSessionRejectUserTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.PREPARE, RecruitmentStatus.RECRUITING);
        FreeSession freeSession = new FreeSession(1L, 1L, sessionBody, sessionEnrollment);
        Student student = Student.of(NsUserTest.SANJIGI.getId(), freeSession.getId());

        freeSession.enroll(student, null);
        freeSession.reject(student);

        Student approvedStudent = SessionDomainTestHelper.getSingleStudent(freeSession);

        assertThat(approvedStudent.isRejected()).isTrue();
    }

}
