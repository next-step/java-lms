package nextstep.courses.dto;

import nextstep.courses.domain.coverImage.CoverImage;

public class CoverImageDto {

    private String path;
    private int size;
    private String imgType;
    private int width;
    private int height;

    public CoverImageDto(String path, int size, String imgType, int width, int height) {
        this.path = path;
        this.size = size;
        this.imgType = imgType;
        this.width = width;
        this.height = height;
    }

    public CoverImage toCoverImage(Long sessionId) {
        return new CoverImage(sessionId, path, size, imgType, width, height);
    }
}
