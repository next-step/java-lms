package nextstep.courses.domain;

import nextstep.courses.domain.type.ApplyStatus;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppliesTest {

    @Test
    @DisplayName("수강 신청 목록에서 수강 승인 개수를 확인할 수 있다")
    public void approval_applies() {
        ChargedSession session = session();
        Apply apply1 = new Apply(session, NsUser.GUEST_USER, ApplyStatus.APPROVAL);
        Apply apply2 = new Apply(session, NsUser.GUEST_USER, ApplyStatus.APPROVAL);
        Apply apply3 = new Apply(session, NsUser.GUEST_USER, ApplyStatus.REFUSAL);
        Applies applies = new Applies(Arrays.asList(apply1, apply2, apply3));

        assertThat(applies.isApprovalCountLessThan(session.maxNumberOfStudent())).isTrue();
    }

    public ChargedSession session() {
        Duration duration = new Duration(LocalDate.now(), LocalDate.now());
        Image image = new Image(1, "JPG", 300, 200);
        return new ChargedSession(duration, new Images(Arrays.asList(image)), 10, BigDecimal.valueOf(10_000));
    }

}
