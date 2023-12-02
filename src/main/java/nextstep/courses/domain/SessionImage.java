package nextstep.courses.domain;

import nextstep.courses.InvalidImageFormatException;

import java.util.Arrays;

public class SessionImage {
    public static int LIMIT_IMAGE_SIZE = 1024 * 1024; // 1MB
    public static int MIN_IMAGE_WIDTH_IN_PIXELS = 300;
    public static int MIN_IMAGE_HEIGHT_IN_PIXELS = 200;
    public static double ASPECT_RATIO = (double) MIN_IMAGE_WIDTH_IN_PIXELS / MIN_IMAGE_HEIGHT_IN_PIXELS;

    private String name;
    private int imageSize;
    private int width;
    private int height;
    private ImageType imageType;

    public static SessionImage imageTypeOf(String inputImageType) throws InvalidImageFormatException {
        ImageType imageType = validateImageType(inputImageType);
        return new SessionImage("tmp", 1024 * 1024, 300, 200, imageType);
    }

    public static SessionImage nameOf(String name) throws InvalidImageFormatException {
        return new SessionImage(name, 1024*1024, 300, 200, ImageType.PNG);
    }

    public static SessionImage valueOf(String name, int imageSize, int width, int height, String inputType) throws InvalidImageFormatException {
        ImageType imageType = validateImageType(inputType);
        return new SessionImage(name, imageSize, width, height, imageType);
    }

    public SessionImage(int imageSize) throws InvalidImageFormatException {
        this("tmp", imageSize, 300, 200, ImageType.GIF);
    }
    public SessionImage(int width, int height) throws InvalidImageFormatException {
        this("tmp",1024 * 1024, width, height, ImageType.GIF);
    }

    private SessionImage(String name, int imageSize, int width, int height, ImageType imageType) throws InvalidImageFormatException {
        validateImageSize(imageSize);
        validateWidthHeight(width, height);

        this.name = name;
        this.imageSize = imageSize;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    private static ImageType validateImageType(String inputType) throws InvalidImageFormatException {
        ImageType type = Arrays.stream(ImageType.values())
                .filter(imageType -> imageType.name().equals(inputType.toUpperCase()))
                .findAny()
                .orElseThrow(()
                        -> new InvalidImageFormatException(String.format("%s는 지원하지 않는 이미지 타입입니다.", inputType)));
        return type;
    }

    private static void validateWidthHeight(int width, int height) throws InvalidImageFormatException {
        if (width < MIN_IMAGE_WIDTH_IN_PIXELS || width <= 0) {
            throw new InvalidImageFormatException("이미지 가로는 300픽셀 이상이어야 합니다.");
        }
        if (height < MIN_IMAGE_HEIGHT_IN_PIXELS || height <= 0) {
            throw new InvalidImageFormatException("이미지 세로는 200픽셀 이상이어야 합니다.");
        }
        if ((double) width / height != ASPECT_RATIO) {
            throw new InvalidImageFormatException("이미지 가로 세로 비율은 3:2여야 합니다.");
        }
    }

    private static void validateImageSize(int imageSize) throws InvalidImageFormatException {
        if (imageSize > LIMIT_IMAGE_SIZE) {
            throw new InvalidImageFormatException("이미지 크기는 1MB 이하만 가능합니다.");
        }
        if (imageSize <= 0) {
            throw new InvalidImageFormatException("이미지 크기는 0MB 이하로는 불가능합니다.");
        }
    }

    @Override
    public String toString() {
        return "SessionImage{" +
                "imageSize=" + imageSize +
                ", width=" + width +
                ", height=" + height +
                ", imageType=" + imageType +
                '}';
    }
}
