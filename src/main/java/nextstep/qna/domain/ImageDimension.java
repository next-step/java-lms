package nextstep.qna.domain;

public class ImageDimension {

    private static final Long MIN_WIDTH = 300L;
    private static final Long MIN_HEIGHT = 200L;
    private static final Double RIGHT_RATIO = (double) 3/2;

    private final Long width;
    private final Long height;

    public ImageDimension(Long width, Long height) {
        validateWidth(width);
        validateHeight(height);
        validateRatio(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateWidth(Long width) {
        if(width == null || width < MIN_WIDTH){
            throw new IllegalArgumentException("이미지의 너비는 300픽셀 이상이어야 합니다.");
        }
    }

    private void validateHeight(Long height) {
        if(height == null || height < MIN_HEIGHT){
            throw new IllegalArgumentException("이미지의 높이는 200픽셀 이상이어야 합니다.");
        }
    }

    private void validateRatio(Long width, Long height) {
        if(width != 0 && height != 0 && (double) width/height == (double) 3/2){
            return;
        }
        throw new IllegalArgumentException("width와 height의 비율은 3:2여야 합니다");
    }
}
