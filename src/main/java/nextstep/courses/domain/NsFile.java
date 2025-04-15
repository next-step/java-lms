package nextstep.courses.domain;

import java.util.Objects;
import java.util.Set;

public class NsFile {
    private static final String FILE_SIZE_TOO_LARGE = "파일 크기는 1MB를 초과할 수 없습니다.";
    private static final String NOT_ALLOWED_IMAGE_TYPE = "유효한 이미지 파일이 아닙니다.";

    private static final long MAX_SIZE = 1_000_000; // 1MB
    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg", "image/jpg", "image/png", "image/gif", "image/svg+xml"
    );

    private final long size;
    private final String type;

    public NsFile(long fileSize, String fileType) {
        validate(fileSize, fileType);
        this.size = fileSize;
        this.type = fileType;
    }

    private void validate(long fileSize, String fileType) {
        if (fileSize > MAX_SIZE) {
            throw new IllegalArgumentException(FILE_SIZE_TOO_LARGE);
        }
        if (!ALLOWED_TYPES.contains(fileType.toLowerCase())) {
            throw new IllegalArgumentException(NOT_ALLOWED_IMAGE_TYPE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NsFile nsFile = (NsFile) o;
        return size == nsFile.size && Objects.equals(type, nsFile.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, type);
    }
}
