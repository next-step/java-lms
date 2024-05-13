package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.courses.CanNotApplyException;
import nextstep.courses.domain.Image.Image;
import nextstep.courses.domain.Image.ImageCapacity;
import nextstep.courses.domain.Image.ImageSize;
import nextstep.courses.domain.Image.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaidSessionTest {

    PaidSession session;

    @BeforeEach
    void init() throws Exception {
        Image image = new Image(new ImageCapacity(1000), ImageType.JPG, new ImageSize(300, 200));
        Period period = new Period(
            LocalDateTime.of(2024, 5, 1, 13, 0),
            LocalDateTime.of(2024, 6, 1, 14, 0));
        SessionStatus status = SessionStatus.READY;

        int fee = 20000;
        int limit = 10;
        session = new PaidSession(image, period, status, fee, limit);
    }

    @Test
    void 지불한_수강료와_강의료가_같아야합니다() {
        int pay = 10000;
        assertThatThrownBy(() -> session.isAppliable(pay, 1))
            .isInstanceOf(CanNotApplyException.class);
    }

    @Test
    void 수강인원이_꽉_차면_에러가_발생합니다() {
        int pay = 20_000;
        int order = 11;
        assertThatThrownBy(() -> session.isAppliable(pay, order))
            .isInstanceOf(CanNotApplyException.class);
    }
}
