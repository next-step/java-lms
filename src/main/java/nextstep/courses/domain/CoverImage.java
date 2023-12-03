package nextstep.courses.domain;

public class CoverImage {
    private static final Long FILE_MAX_SIZE = 1024L*1024L;
    private static final Long WIDTH_MAX_SIZE = 300L;
    private static final Long HEIGHT_MAX_SIZE = 200L;
    private final String name;
    private final CoverImageType coverImageType;
    private final long size;
    private final double width;
    private final double height;

    private CoverImage(String name, CoverImageType coverImageType, long size, double width, double height) {
        if (size >= FILE_MAX_SIZE) {
            throw new IllegalArgumentException("사이즈가 너무 큽니다.");
        }
        if (coverImageType.equals(CoverImageType.NO_MATCH)) {
            throw new IllegalArgumentException("확장자를 확인해주세요.");
        }
        if (width < WIDTH_MAX_SIZE) {
            throw new IllegalArgumentException("width가 작습니다.");
        }
        if (height < HEIGHT_MAX_SIZE) {
            throw new IllegalArgumentException("height이 작습니다.");
        }
        if (Double.compare(width/3, height/2) != 0) {
            throw new IllegalArgumentException("width와 height의 비율이 맞지 않습니다.");
        }

        this.name = name;
        this.coverImageType = coverImageType;
        this.size = size;
        this.width = width;
        this.height = height;
    }

    public static CoverImage defaultOf(String fileName, long size, double width, double height) {
        String name = fileName(fileName);
        CoverImageType coverImageType = fileImageType(fileName);
        return new CoverImage(name, coverImageType,size,width,height);
    }

    public static String fileName(String fileName){
        int point = fileName.indexOf('.');
        return fileName.substring(0, point);
    }
    public static CoverImageType fileImageType(String fileName) {
        int point = fileName.indexOf('.');
        String imageType = fileName.substring(point + 1);
        return CoverImageType.valuesOf(imageType);
    }
}
