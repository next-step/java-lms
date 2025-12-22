package nextstep.sessions.domain.image;

public class FileName {

    static final String ERROR_FILENAME_NOT_EMPTY = "파일명은 빈 값일 수 없습니다";
    private final String value;

    public FileName(String value) {
        validate(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String extension() {
        return value.substring(value.lastIndexOf('.') + 1);
    }

    private void validate(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_FILENAME_NOT_EMPTY);
        }
    }

}
