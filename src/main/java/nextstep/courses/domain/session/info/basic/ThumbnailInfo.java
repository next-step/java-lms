package nextstep.courses.domain.session.info.basic;

import lombok.Getter;
import nextstep.courses.domain.image.ImageFileName;
import nextstep.courses.domain.image.ImageSize;

@Getter
public class ThumbnailInfo {
    private final long fileSize;
    private final ImageFileName fileName;
    private final ImageSize size;

    public ThumbnailInfo(String fullFileName, long fileSize, int width, int height) {
        this.fileSize = fileSize;
        this.fileName = new ImageFileName(fullFileName);
        this.size = new ImageSize(width, height);
    }
}
