package nextstep.courses.domain.session.info.basic;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SessionThumbnail {
    private static final int MAX_FILE_SIZE = 1024 * 1024; // 1MB
    private static final int MAX_THUMBNAIL_COUNT = 5;

    private final List<ThumbnailInfo> thumbnails;

    public SessionThumbnail() {
        this.thumbnails = new ArrayList<>();
    }

    public void addThumbnail(String fullFileName, long fileSize, int width, int height) {
        validateThumbnailCount();
        validateFileSize(fileSize);
        thumbnails.add(new ThumbnailInfo(fullFileName, fileSize, width, height));
    }

    private void validateThumbnailCount() {
        if (thumbnails.size() >= MAX_THUMBNAIL_COUNT) {
            throw new IllegalArgumentException("썸네일은 최대 " + MAX_THUMBNAIL_COUNT + "개까지만 등록할 수 있습니다.");
        }
    }

    private void validateFileSize(long fileSize) {
        if (fileSize > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("썸네일 파일 크기는 1MB를 초과할 수 없습니다.");
        }
    }
}
