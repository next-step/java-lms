package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Image {
    public static final int MB = 1024 * 1024;
    public static final int WIDTH_MIN = 300;
    public static final int HEIGHT_MIN = 200;
    public static final double WIDTH_HEIGHT_RATIO = 1.5;

    private Long id;

    private int imageSize;

    private Type type;

    private int imageWidth;

    private int imageHeight;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum Type {
        GIF("gif"),
        JPG("jpg"),
        JPEG("jpeg"),
        PNG("png"),
        SVG("svg");

        Type(String description) {
            this.description = description;
        }

        private final String description;

        public static Type find(String name) {
            return Arrays.stream(values())
                    .filter(imageType -> imageType.description.equals(name))
                    .findAny()
                    .orElseThrow(
                            () -> new IllegalArgumentException(
                                    String.format("허용하는 확장자는 다음과 같습니다.\n %s", descriptions())
                            )
                    );
        }

        public static String descriptions() {
            StringBuilder sb = new StringBuilder();
            for (Type type : values()) {
                sb.append(type.description).append(", ");
            }
            sb.setLength(sb.length() - 2);

            return sb.toString();
        }
    }

    public Image() {
    }

    public Image(int imageSize, String type, int imageWidth, int imageHeight) {
        this(0L, imageSize, type, imageWidth, imageHeight, LocalDateTime.now(), null);
    }

    public Image(Long id, int imageSize, String type, int imageWidth, int imageHeight,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (imageSize > 1 * MB) {
            throw new IllegalArgumentException("사진 크기는 1MB를 넘을 수 없습니다.");
        }

        if(imageWidth < WIDTH_MIN || imageHeight < HEIGHT_MIN) {
            throw new IllegalArgumentException(
                    String.format("가로 픽셀은 %d, 세로 픽셀은 %d 이상이어야 합니다.", WIDTH_MIN, HEIGHT_MIN)
            );
        }

        if((double) imageWidth / imageHeight != WIDTH_HEIGHT_RATIO) {
            throw new IllegalArgumentException("가로 세로 비율은 3:2여야 합니다.");
        }

        this.id = id;
        this.imageSize = imageSize;
        this.type = Type.find(type);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
