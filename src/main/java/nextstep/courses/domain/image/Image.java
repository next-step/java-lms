package nextstep.courses.domain.image;

import nextstep.courses.domain.BaseTime;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import static nextstep.courses.domain.image.ImageExtension.*;

public class Image extends BaseTime {

    private final Long id;
    private final String fileName;
    private final String coverImageUrl;

    public Image(String fileName, String coverImageUrl) {
        this(0L, fileName, coverImageUrl);
    }

    public Image(Long id, String fileName, String coverImageUrl) {
        validateExtension(fileName);
        validateUri(coverImageUrl);
        this.id = id;
        this.fileName = fileName;
        this.coverImageUrl = coverImageUrl;
    }

    private void validateUri(String imageUrl) {
        try {
            new URI(imageUrl).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException("invalid image uri");
        }
    }

    public Long fetchId() {
        return id;
    }

    public String fetchFileName() {
        return fileName;
    }

    public String fetchCoverImageUrl() {
        return coverImageUrl;
    }
}
