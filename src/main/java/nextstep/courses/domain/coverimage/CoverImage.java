package nextstep.courses.domain.coverimage;

public class CoverImage {
    private final String name;
    private final CoverImageType coverImageType;
    private final ImageFileSize imageFileSize;
    private final ImageSize imageSize;

    private CoverImage(String name, CoverImageType coverImageType, long size, double width, double height) {
        this.name = name;
        this.coverImageType = coverImageType;
        this.imageFileSize = new ImageFileSize(size);
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
