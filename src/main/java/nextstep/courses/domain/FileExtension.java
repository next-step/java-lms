package nextstep.courses.domain;

import java.util.Set;

public class FileExtension {

    private static final Set<String> approvedExtension = Set.of("gif", "jpg", "jpeg", "png", "svg");
    private final String extension;

    public FileExtension(String extension) {
        if (!approvedExtension.contains(extension)) {
            throw new IllegalArgumentException("적절하지 못한 확장자 정보입니다.");
        }
        this.extension = extension;
    }
}
