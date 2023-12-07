package nextstep.courses.domain;

public class CoverImage {
    private static final Long FILE_MAX_SIZE = 1024L*1024L;
    private final String name;
    private final CoverImageType coverImageType;
    private final long size;
    private final ImageSize imageSize;

    private CoverImage(String name, CoverImageType coverImageType, long size, double width, double height) {
        if (size >= FILE_MAX_SIZE) {
            throw new IllegalArgumentException("사이즈가 너무 큽니다.");
        }
        if (coverImageType.equals(CoverImageType.NO_MATCH)) {
            throw new IllegalArgumentException("확장자를 확인해주세요.");
        }

        this.name = name;
        this.coverImageType = coverImageType;
        this.size = size;
        this.imageSize = new ImageSize(width,height);
    }

    public static CoverImage defaultOf(String fileName, long size, double width, double height) {
        String name = fileName(fileName);
        CoverImageType coverImageType = fileImageType(fileName);
        return new CoverImage(name, coverImageType,size,width,height);
    }

    private static String fileName(String fileName){
        int point = fileName.indexOf('.');
        return fileName.substring(0, point);
    }
    private static CoverImageType fileImageType(String fileName) {
        int point = fileName.indexOf('.');
        String imageType = fileName.substring(point + 1);
        return CoverImageType.valuesOf(imageType);
    }
}
