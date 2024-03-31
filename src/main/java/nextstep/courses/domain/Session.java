package nextstep.courses.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.imageio.ImageIO;
import nextstep.courses.InvalidSessionException;

public class Session {

    public static final int WIDTH_MIN_SIZE = 300;
    public static final int HEIGHT_MIN_SIZE = 200;
    public static final double WIDTH_RATIO = 3.0;
    public static final double HEIGHT_RATIO = 2.0;
    public static List<String> ALLOWED_EXT = List.of("gif", "jpg", "jpeg", "png", "svg");
    
    private static final long ONE_MEGA_BYTE = 1024 * 1024;
    private static final double PRECISION = 0.0001;

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private File coverImageFile;
    private Integer price;
    private Integer stock;
    private SessionStatus status = SessionStatus.PREPARE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(LocalDateTime startDate, LocalDateTime endDate, File coverImage, LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, 0, 0, createdAt);
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate, File coverImage, int price, int stock, LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, price, stock, createdAt);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, File coverImageFile,
        Integer price, Integer stock, LocalDateTime createdAt) {
        this.id = id;

        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;

        validateCoverImage(coverImageFile);
        this.coverImageFile = coverImageFile;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidSessionException("강의 종료일이 시작일보다 앞섭니다");
        }
    }

    private void validateCoverImage(File coverImageFile) {
        long fileSize = coverImageFile.length();
        validateCoverImageFileSize(fileSize);

        String fileName = coverImageFile.getName();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        validateCoverImageExt(ext);

        BufferedImage coverImage = loadImage(coverImageFile);
        validateCoverImageSize(coverImage.getWidth(), coverImage.getHeight());
    }

    private BufferedImage loadImage(File coverImageFile) {
        try {
            return ImageIO.read(coverImageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateCoverImageFileSize(long fileSize) {
        if (fileSize > ONE_MEGA_BYTE) {
            throw new InvalidSessionException("강의 커버 이미지 파일 용량이 너무 큽니다");
        }
    }

    private void validateCoverImageExt(String ext) {
        boolean notValidExt = ALLOWED_EXT.stream().noneMatch(ext::equals);
        if (notValidExt) {
            throw new InvalidSessionException("허용되지 않는 강의 커버 이미지 확장자입니다");
        }
    }

    private void validateCoverImageSize(int width, int height) {
        validateCoverImageMinSize(width, height);
        validateCoverImageSizeRatio(width, height);
    }

    private static void validateCoverImageSizeRatio(int width, int height) {
        boolean isValidRatio = Math.abs((width / WIDTH_RATIO) - (height / HEIGHT_RATIO)) < PRECISION;
        if (!isValidRatio) {
            throw new InvalidSessionException("강의 커버 이미지 비율이 유효하지 않습니다");
        }
    }

    private static void validateCoverImageMinSize(int width, int height) {
        if (width < WIDTH_MIN_SIZE || height < HEIGHT_MIN_SIZE) {
            throw new InvalidSessionException("강의 커버 이미지 가로 또는 세로 크기가 너무 작습니다");
        }
    }

    public boolean isFree() {
        return price == 0;
    }
}
