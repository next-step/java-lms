package nextstep.sessions.domain;

public class CoverImage {
    private static final ImageRatio RATIO = new ImageRatio(3.0 / 2.0);

    private final Long id;
    private final Session session;
    private final String fileName;
    private final ImageSize width;
    private final ImageSize height;
    private final FileSize size;
    private final EnableExtension extension;

    public CoverImage(Long id, Session session, String fileName, int width, int height, long size) {
        this(id, session, fileName, ImageSize.width(width), ImageSize.height(height), new FileSize(size), EnableExtension.from(fileName));
    }

    public CoverImage(Long id, Session session, String fileName, ImageSize width, ImageSize height, FileSize size, EnableExtension extension) {
        assertValidExtension(extension);
        assertValidRatio(width, height);

        this.id = id;
        this.session = session;
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        this.size = size;
        this.extension = extension;
    }

    private void assertValidExtension(EnableExtension extension) {
        if (extension == EnableExtension.INVALID) {
            throw new IllegalArgumentException("지원하지 않는 확장자 입니다.");
        }
    }


    private void assertValidRatio(ImageSize width, ImageSize height) {
        if (!RATIO.equals(ImageRatio.ratio(width, height))) {
            throw new IllegalArgumentException("이미지 비율이 적합하지 않습니다.");
        }
    }

}
