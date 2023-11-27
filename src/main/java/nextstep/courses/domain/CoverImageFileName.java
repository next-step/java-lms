package nextstep.courses.domain;

import nextstep.courses.CannotRecruitException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoverImageFileName {

    private static final Pattern EXTENSION_PATTERN = Pattern.compile(".*\\.(jpeg|jpg|png|gif)$");
    private final String filePath;

    public CoverImageFileName(String filePath) {
        validate(filePath);

        this.filePath = filePath;
    }

    private void validate(String filePath) {
        if (isNullOrEmpty(filePath)) {
            throw new CannotRecruitException("이미지 이름은 필수 값입니다.");
        }

        if (!isImageFilePattern(filePath)) {
            throw new CannotRecruitException("이미지 파일 형식이 아닙니다.");
        }
    }

    private boolean isNullOrEmpty(String filePath) {
        return filePath == null || filePath.isBlank();
    }

    private boolean isImageFilePattern(String filePath) {
        Matcher matcher = EXTENSION_PATTERN.matcher(filePath);
        return matcher.matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImageFileName that = (CoverImageFileName) o;
        return Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath);
    }
}
