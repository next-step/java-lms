package nextstep.session;

public class Image {

    private static final int MAXIMUM_IMAGE_SIZE = 1;
    private static final int MAXIMUM_WIDTH = 300;
    private static final int MAXIMUM_HEIGHT = 200;
    private static final String OVER_SIZE_MESSAGE = "이미지 사이즈는 1MB를 초과하면 안됩니다.";
    private static final String OVER_WIDTH_HEIGHT = "이미지의 너비가 300px, 높이가 200px을 초과하면 안됩니다.";
    private static final String NOT_CORRECT_RATE = "이미지의 너비 높이가 3:2 비율이여야 합니다.";

    private final Long id;
    private final String name;
    private final int width;
    private final int height;
    private final int size;

    public Image(Long id, String name, int width, int height, int size) {
        ImageExtension.confirmImageExtension((name));
        confirmRate(width, height);
        confirmWidthHeight(width, height);
        confirmImageSize(size);
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.size = size;
    }

    private void confirmImageSize(int size) {
        if(size > MAXIMUM_IMAGE_SIZE) {
            throw new IllegalArgumentException(OVER_SIZE_MESSAGE);
        }
    }

    private void confirmWidthHeight(int width, int height) {
        if(width > MAXIMUM_WIDTH || height > MAXIMUM_HEIGHT) {
            throw new IllegalArgumentException(OVER_WIDTH_HEIGHT);
        }
    }

    private void confirmRate(int width, int height) {
        int greatestCommonDenominator = getGreatestCommonDenominator(width, height);
        int ratioWidth = width / greatestCommonDenominator;
        int ratioHeight = height / greatestCommonDenominator;

        if (ratioWidth != 3 || ratioHeight != 2) {
            throw new IllegalArgumentException(NOT_CORRECT_RATE);
        }
    }

    private int getGreatestCommonDenominator(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
