package nextstep.courses.domain;

public class FileExtension {

    private final String extension;

    public FileExtension(String extension) {
        if (!ImageExtension.contains(extension)) {
            throw new IllegalArgumentException("적절하지 못한 확장자 정보입니다.");
        }
        this.extension = extension;
    }
}
