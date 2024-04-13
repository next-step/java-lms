package nextstep.session.domain;

import nextstep.common.domain.BaseEntity;
import nextstep.exception.CoverException;
import nextstep.session.dto.CoverVO;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Cover {

    public static final int SIZE_UNIT = 1024;
    public static final int MAXIMUM_MEGABYTE_SIZE = 1;
    private final long id;
    private final Resolution resolution;
    private final ImageFilePath imageFilePath;
    private final long byteSize;
    private final String writerId;
    private final BaseEntity baseEntity;

    public Cover(Resolution resolution, ImageFilePath imageFilePath, long byteSize, String writerId) {
        this(0L, resolution, imageFilePath, byteSize, writerId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Cover(
            long id, Resolution resolution, ImageFilePath imageFilePath, long byteSize,
            String writerId, LocalDateTime createdAt, LocalDateTime lastModifiedAt
    ) {
        this(id, resolution, imageFilePath, byteSize, writerId, new BaseEntity(createdAt, lastModifiedAt));
    }

    public Cover(
            long id, Resolution resolution, ImageFilePath imageFilePath, long byteSize,
            String writerId, BaseEntity baseEntity
    ) {
        validate(byteSize);

        this.id = id;
        this.resolution = resolution;
        this.imageFilePath = imageFilePath;
        this.byteSize = byteSize;
        this.writerId = writerId;
        this.baseEntity = baseEntity;
    }

    private void validate(long byteSize) {
        if ((byteSize / SIZE_UNIT / SIZE_UNIT) > MAXIMUM_MEGABYTE_SIZE) {
            throw new IllegalArgumentException(String.format("이미지는 %dMB 이하여야 합니다.", MAXIMUM_MEGABYTE_SIZE));
        }
    }

    public CoverVO toVO() {
        return new CoverVO(
                this.id, this.resolution.getWidth(), this.resolution.getHeight(), this.imageFilePath.getFilePath(),
                this.imageFilePath.getFileName(), this.imageFilePath.getExtension(), this.byteSize,
                this.writerId, this.baseEntity.isDeleted(), this.baseEntity.getCreatedAt(),
                this.baseEntity.getLastModifiedAt()
        );
    }

    public void delete(NsUser requestUser) {
        validateWriter(requestUser);

        this.baseEntity.delete(LocalDateTime.now());
    }

    private void validateWriter(NsUser requestUser) {
        if (requestUser == null) {
            throw new CoverException("요청 사용자가 존재하지 않습니다.");
        }

        if (!writerId.equals(requestUser.getUserId())) {
            throw new CoverException("삭제 요청자와 작성자가 일치하지 않습니다.");
        }
    }

    public long getId() {
        return id;
    }
}
