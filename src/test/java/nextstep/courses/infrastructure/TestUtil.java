package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImagePixel;
import nextstep.courses.domain.image.ImageType;
import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;

public class TestUtil {
    public static void autoincrementReset(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("ALTER TABLE course ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE image ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE session ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE enrollment ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE ns_user ALTER COLUMN id RESTART WITH 1");
    }

    public static Session createDefaultTddSession() {
        final CoverImage gifImage = new CoverImage(1024L, new ImagePixel(300, 200), ImageType.GIF);
        final CoverImage pngImage = new CoverImage(1024L, new ImagePixel(300, 200), ImageType.PNG);
        final List<CoverImage> imageList = List.of(gifImage, pngImage);

        final Amount amount = Amount.of(100L);
        SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        final Session tddSession = new Session("tdd", sessionPeriod, SessionStatus.PREPARING, imageList, amount,
                new EnrollmentCount(20), RecruitStatus.RECRUITING, JAVAJIGI.getId());
        return tddSession;
    }
}
