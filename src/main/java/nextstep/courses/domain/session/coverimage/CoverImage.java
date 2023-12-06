package nextstep.courses.domain.session.coverimage;

import nextstep.courses.exception.ImageSizeException;
import nextstep.courses.exception.NotExistException;

public class CoverImage {

    private Name name;
    private Size size;

    public CoverImage() {
    }

    public CoverImage(String fileName, int width, int height, long capacitySize) throws ImageSizeException, NotExistException {
        this.name = new Name(fileName);
        this.size = new Size(width, height, capacitySize);
    }
}
