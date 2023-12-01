package nextstep.courses.domain;

import nextstep.courses.domain.type.ApplyStatus;
import nextstep.courses.exception.NotFoundApplyException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppliesTest {

    @Test
    @DisplayName("수강 신청 목록에서 수강 승인 개수를 확인할 수 있다")
    public void approval_applies() {
        ChargedSession session = session();
        Applies applies = applies(session);

        assertThat(applies.isApprovalCountLessThan(session.maxNumberOfStudent())).isTrue();
    }

    @Test
    @DisplayName("수강 신청 목록에서 학생의 수강 신청을 찾을 수 있다")
    public void apply_of_student() {
        Applies applies = applies(session());
        assertThat(applies.of(NsUserTest.JAVAJIGI.getId())).isEqualTo(new Apply(session(), NsUserTest.JAVAJIGI, ApplyStatus.APPROVAL, LocalDateTime.of(2023, 11, 30, 12,12,12)));
    }

    @Test
    @DisplayName("수강 신청 목록에서 학생의 수강 신청을 찾지 못하면 에러 발생한다")
    public void apply_of_student_error() {
        assertThatExceptionOfType(NotFoundApplyException.class)
            .isThrownBy(() -> applies(session()).of(NsUserTest.SANJIGI.getId()))
            .withMessageMatching("지원 내역을 찾을 수 없습니다.");
    }

    public ChargedSession session() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 1, 12, 12, 12);
        Duration duration = new Duration(localDateTime.toLocalDate(), localDateTime.toLocalDate());
        Image image = new Image(1, "JPG", 300, 200, localDateTime);
        return new ChargedSession(duration, new Images(Arrays.asList(image)), 10, BigDecimal.valueOf(10_000), localDateTime);
    }

    private Applies applies(ChargedSession session) {
        Apply apply1 = new Apply(session, NsUserTest.JAVAJIGI, ApplyStatus.APPROVAL, LocalDateTime.of(2023, 11, 30, 12, 12, 12));
        Apply apply2 = new Apply(session, NsUser.GUEST_USER, ApplyStatus.REFUSAL, LocalDateTime.of(2023, 11, 30, 12, 12, 12));
        return new Applies(Arrays.asList(apply1, apply2));
    }

}
