package nextstep.courses.domain.session.coverimage;

import nextstep.courses.exception.ImageFileInfoException;

public class CoverImage {

    private Long id;
    private Name name;
    private Size size;

    public CoverImage() {
    }


    public CoverImage(Long id, String fileName, int width, int height, long capacitySize) throws ImageFileInfoException {
        this.id = id;
        this.name = new Name(fileName);
        this.size = new Size(width, height, capacitySize);
    }

    public String getFileName() {
        return name.getFileName();
    }
}
