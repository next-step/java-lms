package nextstep.session.domain.fixture;

import nextstep.session.domain.ImageType;
import nextstep.session.domain.SessionImage;

public class SessionImageFixture {
    public static final SessionImage sessionImageFixture = SessionImage.of("S3://test.gif", 1024, ImageType.GIF, 300, 200);

}
