package nextstep.courses.dto;

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

    public String getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }

    public String getImgType() {
        return imgType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
