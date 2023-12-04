package nextstep.courses.domain;

public class CoverImage {

    private Long id;
    private final Long sessionId;
    private final CoverImageFileName coverImageFileName;
    private final CoverImageSize coverImageSize;
    private final CoverImagePixel coverImagePixel;

    public CoverImage(Long sessionId, CoverImageFileName coverImageFileName, CoverImageSize coverImageSize, CoverImagePixel coverImagePixel) {
        this.sessionId = sessionId;
        this.coverImageFileName = coverImageFileName;
        this.coverImageSize = coverImageSize;
        this.coverImagePixel = coverImagePixel;
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

}
