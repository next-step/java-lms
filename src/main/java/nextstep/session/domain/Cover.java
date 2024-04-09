package nextstep.session.domain;

import nextstep.common.domain.BaseEntity;
import nextstep.session.dto.CoverDto;

import java.time.LocalDateTime;

public class Cover {

    public static final int SIZE_UNIT = 1024;
    public static final int MAXIMUM_MEGABYTE_SIZE = 1;
    private final long id;
    private final Resolution resolution;
    private final ImageFilePath imageFilePath;
    private final long byteSize;
    private final BaseEntity baseEntity;

    public Cover(Resolution resolution, ImageFilePath imageFilePath, long byteSize) {
        this(0L, resolution, imageFilePath, byteSize, LocalDateTime.now(), LocalDateTime.now());
    }

    public Cover(
            long id, Resolution resolution, ImageFilePath imageFilePath,
            long byteSize, LocalDateTime createdAt, LocalDateTime lastModifiedAt
    ) {
        validate(byteSize);

        this.id = id;
        this.resolution = resolution;
        this.imageFilePath = imageFilePath;
        this.byteSize = byteSize;
        this.baseEntity = new BaseEntity(createdAt, lastModifiedAt);
    }

    private void validate(long byteSize) {
        if ((byteSize / SIZE_UNIT / SIZE_UNIT) > MAXIMUM_MEGABYTE_SIZE) {
            throw new IllegalArgumentException("이미지는 1MB 이하여야 합니다.");
        }
    }

    public CoverDto toDto() {
        return new CoverDto(
                this.id, this.resolution.getWidth(), this.resolution.getHeight(),
                this.imageFilePath.getFilePath(), this.imageFilePath.getFileName(), this.imageFilePath.getExtension(),
                this.byteSize, this.baseEntity.isDeleted(), this.baseEntity.getCreatedAt(), this.baseEntity.getLastModifiedAt()
        );
    }
}
