package nextstep.courses.domain;

import java.util.Objects;

public class CoverImage {

    private final Long id;
    private final Long sessionId;
    private final CoverImageFileName coverImageFileName;
    private final CoverImageSize coverImageSize;
    private final CoverImagePixel coverImagePixel;

    public CoverImage(Long sessionId, CoverImageFileName coverImageFileName, CoverImageSize coverImageSize, CoverImagePixel coverImagePixel) {
        this(0L, sessionId, coverImageFileName, coverImageSize, coverImagePixel);
    }

    public CoverImage(Long id, Long sessionId, CoverImageFileName coverImageFileName, CoverImageSize coverImageSize, CoverImagePixel coverImagePixel) {
        this.id = id;
        this.sessionId = sessionId;
        this.coverImageFileName = coverImageFileName;
        this.coverImageSize = coverImageSize;
        this.coverImagePixel = coverImagePixel;
    }

    public Long id() {
        return this.id;
    }

    public Long sessionId() {
        return this.sessionId;
    }

    public String fileName() {
        return this.coverImageFileName.getFileName();
    }

    public int fileSize() {
        return this.coverImageSize.getImageSize();
    }

    public int width() {
        return this.coverImagePixel.getWidth();
    }

    public int height() {
        return this.coverImagePixel.getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImage that = (CoverImage) o;
        return Objects.equals(id, that.id) && Objects.equals(sessionId, that.sessionId) && Objects.equals(coverImageFileName, that.coverImageFileName) && Objects.equals(coverImageSize, that.coverImageSize) && Objects.equals(coverImagePixel, that.coverImagePixel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, coverImageFileName, coverImageSize, coverImagePixel);
    }
}
