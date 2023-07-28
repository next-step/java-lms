package nextstep.users.domain;

import nextstep.courses.domain.CoverImage;

public class CoverImageTest {
    public static final CoverImage coverImage = new CoverImage(1L, "./image", "session1.jpg", 1024L);
    public static final CoverImage coverImage2 = new CoverImage(2L, "./image", "session2.jpg", 1024L);
}
