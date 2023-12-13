package nextstep.courses.domain;


public class CoverImage {
    private final String name;
    private final ImageExtension extension;
    private final Long byteSize;
    private final Double width;
    private final Double height;


    public CoverImage(String name, Long byteSize, Double width, Double height) {
        this.name = name;
        String[] splitName = name.split("\\.");
        this.extension = ImageExtension.from(splitName[splitName.length - 1]);
        validateByteSize(byteSize);
        this.byteSize = byteSize;
        validateWidthHeight(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateByteSize(Long byteSize) {
        if(byteSize > 1000L){
            throw new IllegalArgumentException();
        }
    }

    private void validateWidthHeight(Double width, Double height){
        if(width < 300||height < 200){
            throw new IllegalArgumentException();
        }

        if(width/height != 1.5){
            throw new IllegalArgumentException();
        }
    }
}
