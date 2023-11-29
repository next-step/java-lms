package nextstep.courses.domain;

public class Image {
    private String type;
    private int width;
    private int height;

    public Image() {
    }

    public Image(String type, int width, int height) {
        validateImage(type, width, height);
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private static void validateImage(String type, int width, int height) {
        if (width * height > 1024 * 1024) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다.");
        }

        if (!(type.contains("gif") || type.contains("jpg") || type.contains("jpeg") || type.contains("png") || type.contains("svg"))) {
            throw new IllegalArgumentException("이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.");
        }

        if (width < 300) {
            throw new IllegalArgumentException("이미지 넓이는 300px 이상이여야 한다.");
        }

        if (height < 200) {
            throw new IllegalArgumentException("이미지 높이는 200px 이상이여야 한다.");
        }

        if (width / height != 3 / 2) {
            throw new IllegalArgumentException("넓이와 높이의 비율은 3:2여야 한다.");
        }
    }
}
