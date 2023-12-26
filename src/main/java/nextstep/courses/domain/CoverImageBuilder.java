package nextstep.courses.domain;

public class CoverImageBuilder {
    private String name = "pobi.png";
    private Long byteSize = 500L;
    private Double width = 300D;
    private Double height = 200D;

    private CoverImageBuilder(){
    }

    private CoverImageBuilder(final CoverImageBuilder coverImageBuilder){
        this.name = coverImageBuilder.name;
        this.byteSize = coverImageBuilder.byteSize;
        this.width = coverImageBuilder.width;
        this.height = coverImageBuilder.height;
    }

    public static CoverImageBuilder aCoverImage(){
        return new CoverImageBuilder();
    }

    public CoverImageBuilder withName(final String name) {
        this.name = name;
        return new CoverImageBuilder(this);
    }

    public CoverImageBuilder withByteSize(final Long byteSize) {
        this.byteSize = byteSize;
        return new CoverImageBuilder(this);
    }

    public CoverImageBuilder withWidth(final Double width) {
        this.width = width;
        return new CoverImageBuilder(this);
    }

    public CoverImageBuilder withHeight(final Double height) {
        this.height = height;
        return new CoverImageBuilder(this);
    }

    public CoverImage build() {
        return new CoverImage(this.name, this.byteSize, this.width, this.height);
    }
}
