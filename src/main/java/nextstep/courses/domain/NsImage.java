package nextstep.courses.domain;

import java.util.Objects;

public class NsImage {

    private final NsFile nsFile;
    private final NsImageSize size;

    public NsImage(long fileSize, String fileType, int width, int height) {
        this.nsFile = new NsFile(fileSize, fileType);
        this.size = new NsImageSize(width, height);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NsImage nsImage = (NsImage) o;
        return Objects.equals(nsFile, nsImage.nsFile) && Objects.equals(size, nsImage.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsFile, size);
    }
}
