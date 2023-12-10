package nextstep.courses.domain.Image;

public class CoverImage {

    private Long id;

    private CoverImageSize coverImageSize;

    private CoverImgaePixel coverImgaePixel;

    private CoverImageType coverImageType;

    public CoverImage(Long id, CoverImageSize coverImageSize, CoverImgaePixel coverImgaePixel, CoverImageType coverImageType) {
        this.id = id;
        this.coverImageSize = coverImageSize;
        this.coverImgaePixel = coverImgaePixel;
        this.coverImageType = coverImageType;
    }
}
