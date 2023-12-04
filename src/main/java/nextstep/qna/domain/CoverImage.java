package nextstep.qna.domain;

import ch.qos.logback.core.util.FileSize;

public class CoverImage {
    private String url;
    private FileSize size;
    private String type;
    private Pixel width;
    private Pixel height;

    private CoverImage(String url, FileSize size, String type, Pixel width, Pixel height) {
        ratioCheck(width, height);
        typeCheck(type);
        sizeCheck(size);

        this.url = url;
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public static CoverImage of(String url, FileSize size, String type, Pixel width, Pixel height) {
        return new CoverImage(url, size, type, width, height);
    }

    private void ratioCheck(Pixel width, Pixel height) {
        // TODO check width , height ratio
    }

    private void typeCheck(String type) {
        // TODO check type
    }

    private void sizeCheck(FileSize size) {
        // TODO check size
    }
}
