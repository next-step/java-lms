package nextstep.courses.domain;

import java.util.Objects;

public class SessionImage {
    
    private long size;
    private SessionImageType imageType;
    private int width;
    private int height;

    public SessionImage(long size, SessionImageType imageType, int width, int height) {
        
        if (size > 1_048_576L) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }

        // if (!SessionImageType.isValid(imageType.toString())) {
        //     throw new IllegalArgumentException("허용되지 않는 이미지 타입입니다.");
        // }

        if (width < 300) {
            throw new IllegalArgumentException("이미지의 최소 너비는 300px입니다.");
        }

        if (height < 200) {
            throw new IllegalArgumentException("이미지의 최소 높이는 200px입니다.");
        }

        if (Math.abs((width / (double) height) - 1.5) > 0.01) {
            throw new IllegalArgumentException("이미지 비율은 3:2여야 합니다.");
        }
        
        this.imageType = imageType;
        this.size = size;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionImage that = (SessionImage) o;
        return size == that.size &&
            width == that.width &&
            height == that.height &&
            imageType == that.imageType; // enum 비교는 equals가 아닌 == 사용해도 안전
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, imageType, width, height);
    }

    @Override
    public String toString() {
        return "SessionImage{" +
                "size=" + size +
                ", imageType=" + imageType +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

}
