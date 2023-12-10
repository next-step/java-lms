package nextstep.courses.domain;

public class ImageSize {

    private static int BYTES_OF_1MB = 1048576;

    private int size;

    private int width;

    private int height;

    public ImageSize(int width, int height) {
        if (width < 300) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀 이상이어야 한다.");
        }
        if (height < 200) {
            throw new IllegalArgumentException("이미지의 height는 200픽셀 이상이어야 한다.");
        }
        if ((float) width / height != (float) 3 / 2) {
            throw new IllegalArgumentException("이미지의 width와 height의 비율은 3:2여야 한다.");
        }
        if (BYTES_OF_1MB < width * height * 3) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
        this.width = width;
        this.height = height;
        this.size = width * height * 3;
    }

    public int getSize() {
        return this.size;
    }
}
