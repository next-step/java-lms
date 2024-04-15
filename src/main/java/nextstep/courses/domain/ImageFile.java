package nextstep.courses.domain;

import java.io.File;
import java.util.Set;

public class ImageFile {

    private final ImageInfo imageInfo;
    private final FileExtension extension;


    public ImageFile(ImageInfo imageInfo, String extension) {
        this.imageInfo = imageInfo;
        this.extension = new FileExtension(extension);
    }

    public boolean hasApprovedExtension() {
        return false;
    }

}
