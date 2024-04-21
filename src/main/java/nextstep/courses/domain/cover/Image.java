package nextstep.courses.domain.cover;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.entity.ImageEntity;

public class Image extends BaseEntity {

    private Long id;

    private final ImageSize size;

    private final ImageType type;

    private final ImageWidth width;

    private final ImageHeight height;

    public boolean isThreeToTwoRatio() {
        double ratio = (double) width.getValue() / height.getValue();
        return Math.abs(ratio - 1.5) < 0.001; // 허용 가능한 오차 범위 내에서 비율이 3:2인지 확인
    }

    public Image(ImageEntity imageEntity){
        this(imageEntity.getId(),
            new ImageSize(imageEntity.getSize()),
            ImageType.valueOf(imageEntity.getType()),
            new ImageWidth(imageEntity.getWidth()),
            new ImageHeight(imageEntity.getHeight()));
    }

    public Image(ImageSize size, ImageType type, ImageWidth width, ImageHeight height) {
        this(null, size, type, width, height);
    }
    public Image(Long id, ImageSize size, ImageType type, ImageWidth width, ImageHeight height) {
        this.id = id;
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public int getSize() {
        return size.getValue();
    }

    public String  getType() {
        return type.getValue();
    }

    public int getWidth() {
        return width.getValue();
    }

    public int getHeight() {
        return height.getValue();
    }
}
