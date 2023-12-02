package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplyTest {

    @Test
    @DisplayName("담당 강사가 승인을 할 경우 수강신청 내역은 승인이 된다.")
    void approve() {
        Session session = createSession();
        Apply apply = new Apply(session, NsUserTest.JAVAJIGI, ApplyStatus.APPLYING);
        apply.approve(1L);

        ApplyStatus actual = apply.applyStatus();

        Assertions.assertThat(actual).isEqualTo(ApplyStatus.APPROVAL);
    }

    @Test
    @DisplayName("담당 강사가 거절을 할 경우 수강신청 내역은 승인이 된다.")
    void refuse() {
        Session session = createSession();
        Apply apply = new Apply(session, NsUserTest.JAVAJIGI, ApplyStatus.APPLYING);
        apply.refuse(1L);

        ApplyStatus actual = apply.applyStatus();

        Assertions.assertThat(actual).isEqualTo(ApplyStatus.REFUSAL);
    }

    @Test
    @DisplayName("담당 강사가 아니라면 승인시 오류가 발생한다.")
    void approve_fail() {
        Session session = createSession();
        Apply apply = new Apply(session, NsUserTest.JAVAJIGI, ApplyStatus.APPLYING);

        assertThatThrownBy(() -> apply.refuse(2L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 강의의 강사만 가능합니다.");
    }

    @Test
    @DisplayName("담당 강사가 아니라면 거절시 오류가 발생한다.")
    void refuse_fail() {
        Session session = createSession();
        Apply apply = new Apply(session, NsUserTest.JAVAJIGI, ApplyStatus.APPLYING);

        assertThatThrownBy(() -> apply.refuse(2L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 강의의 강사만 가능합니다.");
    }

    private Session createSession() {
        SessionDuration sessionDuration = new SessionDuration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        CoverImage coverImage = new CoverImage(1L, new CoverImageFileName("test.png"), new CoverImageSize(300), new CoverImagePixel(300, 200));
        SessionEnrolment sessionEnrolment = new SessionEnrolment(new SessionStudent(new Students()), SessionStatusType.ONGOING, new Amount(0L), true);

        Session session = new Session(1L, sessionDuration, sessionEnrolment, new CoverImages(List.of(coverImage)));
        session.updateInstructor(1L);

        return session;
    }
}
