package nextstep.courses.domain.session.coverimage;

import nextstep.courses.exception.ImageFileInfoException;

public class CoverImage {

    private Long id;
    private Name name;
    private Size size;

    public CoverImage() {
    }


    public CoverImage(Long id, String fileName, int width, int height, long capacitySize) {
        this.id = id;
        try {
            this.name = new Name(fileName);
            this.size = new Size(width, height, capacitySize);
        } catch (ImageFileInfoException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
