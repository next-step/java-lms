package nextstep.courses.domain;

public class SessionImageSize {

    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;
    private static final int WIDTH_MAXIMUM = 300;
    private static final int HEIGHT_MAXIMUM = 200;

    private int width;
    private int height;
    private String ratio;

    public SessionImageSize(int width, int height) {
        validation(width, height);
        this.width = width;
        this.height = height;
        this.ratio = calculateRatio();
    }

    private void validation(int width, int height) {
        widthAndHeightValidate(width, height);
        ratioValidate(width, height);
    }

    private void ratioValidate(int width, int height) {
        if (WIDTH_RATIO != calculaateWidthRatio(width) && HEIGHT_RATIO != calculaateHeightRatio(height)) {
            throw new IllegalArgumentException(" 이미지 비율을 3:2만 가능합니다.");
        }
    }

    private void widthAndHeightValidate(int width, int height) {
        if (width < WIDTH_MAXIMUM || height < HEIGHT_MAXIMUM) {
            throw new IllegalArgumentException(" 이미지의 크기는 세로 300, 가로 200 픽셀 이상만 가능합니다. ");
        }
    }

    private String calculateRatio() {
        return calculaateWidthRatio(width) + ":" + calculaateHeightRatio(height);
    }

    private int calculaateWidthRatio(int width) {
        int divided = width / 100;
        if(WIDTH_RATIO != divided){
            divided = divided / WIDTH_RATIO;
        }
        return divided;
    }

    private int calculaateHeightRatio(int height) {
        int divided = height / 100;
        if(HEIGHT_RATIO != divided){
            divided = divided / HEIGHT_RATIO;
        }
        return divided;
    }
}
