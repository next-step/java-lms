package nextstep.courses.domain;

import java.util.regex.Pattern;
import nextstep.courses.exception.FileException.FileExtensionException;

public class Thumbnail {

    private static final Pattern IMAGE_EXTENSION_PATTERN = Pattern.compile(".*\\.(gif|jpe?g|png|svg)$", Pattern.CASE_INSENSITIVE);
    private final Integer thumbnailId;
    private final String thumbnailName;
    private final ThumbnailSize thumbnailSize;
    private final ThumbnailDimensions thumbnailDimensions;

    public Thumbnail(Integer thumbnailId, String thumbnailName,
                     long thumbnailSize, int thumbnailWidth, int thumbnailHeight) {
        this(thumbnailId, thumbnailName, new ThumbnailSize(thumbnailSize),
                new ThumbnailDimensions(thumbnailWidth, thumbnailHeight));
    }

    public Thumbnail(Integer thumbnailId, String thumbnailName,
                     ThumbnailSize thumbnailSize, ThumbnailDimensions thumbnailDimensions) {
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
