package nextstep.courses.domain.vo;

public class ImageSize {

    private int width;
    private int height;

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public boolean biggerThanEqual(int width, int height) {
        return this.width >= width && this.height >= height;
    }

    public boolean satisfyProportion(int widthProportion, int heightProportion) {
        return (double) width / height == (double) widthProportion / heightProportion;
    }
}
