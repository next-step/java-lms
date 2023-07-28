package nextstep.courses.domain;

public class CoverImage {

    Long id;
    String imagePath;
    String fileName;
    Long size;

    public CoverImage(Long id, String imagePath, String fileName, Long size) {
        this.id = id;
        this.imagePath = imagePath;
        this.fileName = fileName;
        this.size = size;
    }
}
