package nextstep.courses.domain.session.coverimage;

import nextstep.courses.exception.ImageFileInfoException;

public class CoverImage {

    private Name name;
    private Size size;

    public CoverImage() {
    }

    public CoverImage(String fileName, int width, int height, long capacitySize) throws ImageFileInfoException {
        this.name = new Name(fileName);
        this.size = new Size(width, height, capacitySize);
    }
}
