package nextstep.courses.domain;

import nextstep.courses.utils.BaseEntity;

import java.time.LocalDateTime;

public class SessionCover extends BaseEntity {
    public static final double RATIO = (double) 3 / 2;

    private Long id;
    private final byte[] image;


    public SessionCover(byte[] image) {
        this.image = image;
    }

    public SessionCover(Long id, byte[] image, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SessionCover(long id, int width, int height, long size, byte[] image) {
        validateSize(size);
        validatePixel(width, height);
        validateRatio(width, height);
        if (image == null) {
            throw new IllegalArgumentException("이미지 값이 없습니다.");
        }
        this.image = image;
        this.id = id;
    }

    private static void validateSize(long size) {
        if (size > 1024 * 1024) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야합니다.");
        }
    }

    private static void validatePixel(int width, int height) {
        if (height < 200 || width < 300) {
            throw new IllegalArgumentException("가로 300픽셀 세로 200픽셀 이상이어야합니다.");
        }
    }

    private static void validateRatio(int width, int height) {
        if (Math.abs((double) width / height - RATIO) > 0.01) {
            throw new IllegalArgumentException("가로 세로 비율을 3:2로 맞춰주세요.");
        }
    }

    public byte[] image() {
        return image;
    }

    public Long id() {
        return id;
    }
}
