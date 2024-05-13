package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import nextstep.courses.CanNotApplyException;
import nextstep.courses.domain.Image.Image;
import nextstep.courses.domain.Image.ImageCapacity;
import nextstep.courses.domain.Image.ImageSize;
import nextstep.courses.domain.Image.ImageType;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionTest {

    Image image;
    Period period;
    SessionStatus status;

    Session session;

    @BeforeEach
    void init() throws Exception {
        image = new Image(new ImageCapacity(1000), ImageType.JPG, new ImageSize(300, 200));
        period = new Period(
            LocalDateTime.of(2024, 5, 1, 13, 0),
            LocalDateTime.of(2024, 6, 1, 14, 0));
        status = SessionStatus.RECRUIT;
    }

    @Test
    void 수강신청은_모집중일때만() {
        SessionStatus endStatus = SessionStatus.END;
        session = new FreeSession(image, period, endStatus);
        NsUser user = NsUserTest.JAVAJIGI;
        Assertions.assertThatThrownBy(() -> session.apply(0, user))
            .isInstanceOf(CanNotApplyException.class);
    }

    @Test
    void 무료_수강신청_성공() throws Exception {
        session = new FreeSession(image, period, status);
        NsUser user = NsUserTest.JAVAJIGI;
        session.apply(0, user);
        Assertions.assertThat(session.getUsers().numberOfUsers()).isEqualTo(1);
    }

    @Test
    void 유료_수강신청_성공() throws Exception {
        session = new PaidSession(image, period, status, 20_000, 10);
        NsUser user = NsUserTest.JAVAJIGI;
        session.apply(20_000, user);
        Assertions.assertThat(session.getUsers().numberOfUsers()).isEqualTo(1);
    }

}
