package nextstep.courses.domain;

public class CoverImage {

    private final CoverImageFileName coverImageFileName;
    private final CoverImageSize coverImageSize;
    private final CoverImagePixel coverImagePixel;

    public CoverImage(CoverImageFileName coverImageFileName, CoverImageSize coverImageSize, CoverImagePixel coverImagePixel) {
        this.coverImageFileName = coverImageFileName;
        this.coverImageSize = coverImageSize;
        this.coverImagePixel = coverImagePixel;
    }
}
