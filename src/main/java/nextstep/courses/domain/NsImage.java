package nextstep.courses.domain;

public class NsImage {

    private final NsFile nsFile;
    private final NsImageSize size;

    public NsImage(long sizeInBytes, String contentType, int width, int height) {
        this.nsFile = new NsFile(sizeInBytes, contentType);
        this.size = new NsImageSize(width, height);
    }
}
