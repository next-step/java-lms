package nextstep.courses.domain;

import java.util.regex.Pattern;
import nextstep.courses.exception.FileException.FileExtensionException;

public class Thumbnail {

    private static final Pattern IMAGE_EXTENSION_PATTERN = Pattern.compile(".*\\.(gif|jpe?g|png|svg)$", Pattern.CASE_INSENSITIVE);
    private final Integer thumbnailId;
    private final String thumbnailName;
    private final FileSize thumbnailSize;
    private final FileDimensions thumbnailDimensions;

    public Thumbnail(Integer thumbnailId, String thumbnailName,
                     FileSize thumbnailSize, FileDimensions thumbnailDimensions) {
        validateThumbnailExt(thumbnailName);
        this.thumbnailId = thumbnailId;
        this.thumbnailName = thumbnailName;
        this.thumbnailSize = thumbnailSize;
        this.thumbnailDimensions = thumbnailDimensions;
    }

    private void validateThumbnailExt(String thumbnailName) {
        if (!isValidExtension(thumbnailName)) {
            throw new FileExtensionException("파일 확장자는 gif, jpe, jpeg, png, svg만 가능합니다.");
        }
    }

    private static boolean isValidExtension(String thumbnailName) {
        return IMAGE_EXTENSION_PATTERN.matcher(thumbnailName).matches();
    }
}
