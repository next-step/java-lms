package nextstep.sessions.domain;

import nextstep.sessions.domain.exception.SessionsException;

public class CoverImage {

    private static final int IMAGE_FILE_SIZE_LIMIT = 1024 * 1024;

    private String name;
    private ImageType type;
    private int size;
    private int width;
    private int height;
    private String path;

    public CoverImage(String name, int size, int width, int height, String path) {
        validateFileSize(size);
        this.type = ImageType.valueOfName(name);
        this.name = name;
        this.size = size;
        this.width = width;
        this.height = height;
        this.path = path;
    }

    private void validateFileSize(int size) {
        if (size > IMAGE_FILE_SIZE_LIMIT) {
            throw new SessionsException("이미지 파일 크기가 초과했습니다.");
        }
    }

}
