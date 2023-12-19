package nextstep.courses.dto;

import nextstep.courses.domain.ImageExtension;

public class CoverImageDTO{
    private final String name;
    private final ImageExtension extension;
    private final Long byteSize;
    private final Double width;
    private final Double height;

    public CoverImageDTO(String name, ImageExtension extension, Long byteSize, Double width, Double height) {
        this.name = name;
        this.extension = extension;
        this.byteSize = byteSize;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public ImageExtension getExtension() {
        return extension;
    }

    public Long getByteSize() {
        return byteSize;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }

}
