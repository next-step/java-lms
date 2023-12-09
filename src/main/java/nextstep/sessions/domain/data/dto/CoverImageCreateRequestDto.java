package nextstep.sessions.domain.data.dto;

import nextstep.sessions.domain.data.vo.CoverImage;

public class CoverImageCreateRequestDto {

    private String fileName;
    private int fileSize;
    private int width;
    private int height;

    public CoverImage toCoverImage() {
        return new CoverImage(fileName, fileSize, width, height);
    }

}
