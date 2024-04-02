package nextstep.sessions.domain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class CoverImage {

    private static final long MAX_fileSize = 1024 * 1024; // 1MB
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    private static final double STANDARD_RATIO = 3.0 / 2.0;
    private static final double RATIO_OFFSET = 0.01;
    private static final Set<String> SUPPORT_EXTENSION = Set.of("gif", "jpg", "jpeg", "png", "svg");

    private Long id;
    private int width;
    private int height;
    private long fileSize;

    private String fileName;
    private String extension;
    private String filePath;

    private CoverImage() {
    }

    public CoverImage(int width, int height, long fileSize, String fileName, String extension, String filePath) {
        this(0L, width, height, fileSize, fileName, extension, filePath);
    }

    public CoverImage(Long id, int width, int height, long fileSize, String fileName, String extension, String filePath) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("최소 너비는 " + MIN_WIDTH + " pixel 입니다");
        }

        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("최소 높이는 " + MIN_HEIGHT + " pixel 입니다");
        }

        if (fileSize > MAX_fileSize) {
            throw new IllegalArgumentException("최대 파일 크기를 초과하였습니다");
        }

        if (!SUPPORT_EXTENSION.contains(extension.toLowerCase())) {
            throw new IllegalArgumentException("지원하지 않는 확장자입니다");
        }

        double ratio = (double) width / height;
        if (Math.abs(ratio - STANDARD_RATIO) > RATIO_OFFSET) {
            throw new IllegalArgumentException("비율이 적합하지 않습니다");
        }

        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("파일명이 비어있습니다");
        }

        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("파일 경로가 비어있습니다");
        }

        this.id = id;
        this.width = width;
        this.height = height;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.extension = extension.toLowerCase();
        this.filePath = filePath;
    }

    public static CoverImage from(File file) throws IOException {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.indexOf(".") + 1);
        long fileSize = file.length();
        String filePath = file.getPath();

        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();

        return new CoverImage(width, height, fileSize, fileName, extension, filePath);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        CoverImage that = (CoverImage) other;
        return width == that.width && height == that.height && Objects.equals(extension, that.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, extension);
    }

    @Override
    public String toString() {
        return "CoverImage{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", fileSize=" + fileSize +
                ", fileName='" + fileName + '\'' +
                ", extension='" + extension + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
