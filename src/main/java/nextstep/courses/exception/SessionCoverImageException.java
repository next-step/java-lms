package nextstep.courses.exception;

import nextstep.courses.domain.session.ImageExtension;

import java.text.MessageFormat;

import static nextstep.courses.domain.session.SessionCoverImage.*;

public class SessionCoverImageException extends SessionException {

    public SessionCoverImageException(long size, int width, int height, String extension) {
        super(SessionExceptionMessage.UNSUPPORTED_IMAGE_FORMAT,
                MessageFormat.format("지원 형식(파일 크기: {0}, 비율: {1}x{2} pixels, 확장자: {3}), " +
                                "입력 형식(파일 크기: {4}, 비율: {5}x{6} pixels, 확장자: {7})"
                , MAX_FILE_SIZE, MIN_WIDTH, MIN_HEIGHT, ImageExtension.values()
                , size, width, height, extension));
    }

}
