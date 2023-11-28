package nextstep.courses.domain;

import java.util.Objects;

public class CoverImageFileName {

    private static final int START_INDEX = 0;
    private static final int EXTENSION_INDEX = 1;
    private static final String DOT = ".";
    private final String fileName;

    public CoverImageFileName(String fullFileName) {
        validate(fullFileName);

        this.fileName = fullFileName;
    }

    private void validate(String fullFileName) {
        if (isNullOrEmpty(fullFileName)) {
            throw new IllegalArgumentException("파일명은 필수입니다.");
        }

        String fileName = findFileName(fullFileName);
        String extension = findExtension(fullFileName);

        if (isNullOrEmpty(fileName)) {
            throw new IllegalArgumentException("확장자를 제외한 파일이름은 필수 입니다.");
        }

        if (!ImageExtension.isPossible(extension)) {
            throw new IllegalArgumentException("이미지 파일 형식이 아닙니다.");
        }
    }

    private boolean isNullOrEmpty(String fileName) {
        return fileName == null || fileName.isBlank();
    }

    private String findExtension(String fullFileName) {
        return fullFileName.substring(findDotIndex(fullFileName) + EXTENSION_INDEX);
    }

    private String findFileName(String fullFileName) {
        return fullFileName.substring(START_INDEX, findDotIndex(fullFileName));
    }

    private int findDotIndex(String fileName) {
        int dotIndex = fileName.lastIndexOf(DOT);

        if (dotIndex == -1) {
            throw new IllegalArgumentException("정상적인 파일 이름이 아닙니다.");
        }

        return dotIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImageFileName that = (CoverImageFileName) o;
        return Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName);
    }
}
