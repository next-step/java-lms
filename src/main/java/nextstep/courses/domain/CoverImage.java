package nextstep.courses.domain;

public class CoverImage {

    private final Long id;
    private final String imagePath;
    private final String fileName;
    private final Long size;

    public CoverImage(Long id, String imagePath, String fileName, Long size) {
        this.id = id;
        this.imagePath = imagePath;
        this.fileName = fileName;
        this.size = size;
    }
}
