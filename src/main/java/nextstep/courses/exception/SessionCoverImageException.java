package nextstep.courses.exception;

import java.text.MessageFormat;
import java.util.List;

import static nextstep.courses.domain.image.ImageFileSize.MAX_FILE_SIZE;
import static nextstep.courses.domain.image.ImageSize.*;
import static nextstep.courses.exception.SessionExceptionMessage.UNSUPPORTED_IMAGE_FORMAT;

public class SessionCoverImageException extends SessionException {

    public SessionCoverImageException(long size) {
        this(MessageFormat.format("이미지 파일 크기는 {0}bytes 이하여야 합니다. (입력된 이미지 파일 크기: {1})"
                , MAX_FILE_SIZE, size));
    }

    public SessionCoverImageException(int width, int height) {
        this(MessageFormat.format("이미지 가로는 {0}pixels 세로는 {1}pixels 이상 이어야 하며, 가로 x 세로 비율은 {2} x {3} 이어야 합니다. (입력된 이미지 width x height: {4} x {5})"
                , MIN_WIDTH, MIN_HEIGHT, WIDTH_RATIO, HEIGHT_RATIO, width, height));
    }

    public SessionCoverImageException(List<String> extensions, String extension) {
        this(MessageFormat.format("이미지 확장자는 {0} 형식만 허용합니다. (입력된 이미지 확장자: {1})"
                , extensions, extension));
    }

    private SessionCoverImageException(String detailMessage) {
        super(UNSUPPORTED_IMAGE_FORMAT, detailMessage);
    }

}
