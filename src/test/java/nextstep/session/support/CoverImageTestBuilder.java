package nextstep.session.support;

import nextstep.session.domain.image.CoverImage;

public class CoverImageTestBuilder {
    private Long id = 1L;
    private String fileName = "DefaultFileName";
    private String imageFormat = "jpg";
    private long fileSize = 1024L;
    private int width = 300;
    private int height = 200;
    private Long sessionId = 1L;

    public CoverImageTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CoverImageTestBuilder withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public CoverImageTestBuilder withImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
        return this;
    }

    public CoverImageTestBuilder withFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public CoverImageTestBuilder withImageSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public CoverImageTestBuilder withSessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public CoverImage build() {
        return new CoverImage.Builder()
                .id(id)
                .fileName(fileName)
                .imageFormat(imageFormat)
                .fileSize(fileSize)
                .imageSize(width, height)
                .sessionId(sessionId)
                .build();
    }
}
