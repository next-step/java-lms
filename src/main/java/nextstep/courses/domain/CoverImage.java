package nextstep.courses.domain;


public class CoverImage {
    public static final long LIMIT_BYTE_SIZE = 1000L;
    private final String name;
    private final ImageExtension extension;
    private final Long byteSize;
    private final Double width;
    private final Double height;


    public CoverImage(String name, Long byteSize, Double width, Double height) {
        validateByteSize(byteSize);
        validateWidthHeight(width, height);
        this.name = name;
        String[] splitName = name.split("\\.");
        this.extension = ImageExtension.from(splitName[splitName.length - 1]);
        this.byteSize = byteSize;
        this.width = width;
        this.height = height;
    }

    private void validateByteSize(Long byteSize) {
        if(byteSize > LIMIT_BYTE_SIZE){
            throw new IllegalArgumentException(ExceptionMessage.COVER_IMAGE_BYTE_SIZE.getMessage());
        }
    }

    private void validateWidthHeight(Double width, Double height){
        if(width < 300||height < 200){
            throw new IllegalArgumentException(ExceptionMessage.COVER_IMAGE_LEAST_SIZE.getMessage());
        }

        if(width/height != 1.5){
            throw new IllegalArgumentException(ExceptionMessage.COVER_IMAGE_RATIO.getMessage());
        }
    }
}
