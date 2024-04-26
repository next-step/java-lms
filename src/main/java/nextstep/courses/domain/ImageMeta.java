package nextstep.courses.domain;

public class ImageMeta {

    private static final double RATIO = (double) Math.round(3.0 / 2.0 * 100) / 100;
    private static final int MAX_FILE_SIZE = 1;
    private final ImageSize imageSize;
    private final int imageFileSize;
    private final String ext;

    public ImageMeta(int width, int height, int imageFileSize, String ext) {
        validate(imageFileSize, ext);
        this.imageSize = new ImageSize(width, height);
        this.imageFileSize = imageFileSize;
        this.ext = ext;
    }

    private void validate(int imageFileSize, String ext){
        if (!isValidFileSize(imageFileSize)) {
            throw new IllegalArgumentException(
                    String.format("강의 커버 이미지는 최대 크기는 %d 입니다.", MAX_FILE_SIZE));
        }

        if (!ImageExtension.isAllowed(ext)) {
            throw new IllegalArgumentException(
                    String.format("파일 확장자가 올바르지 않습니다. 해당 파일 확장자는 %s 입니다.", ext));
        }
    }

    private boolean isValidFileSize(int imageFileSize) {
        return toMb(imageFileSize) < MAX_FILE_SIZE;
    }

    private long toMb(int amount){
        return amount / (1_000 * 1_000);
    }
}
