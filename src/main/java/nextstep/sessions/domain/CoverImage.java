package nextstep.sessions.domain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CoverImage {

    private static final long MAX_SIZE = 1024 * 1024; // 1MB
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    private static final double STANDARD_RATIO = 3.0 / 2.0;
    private static final double RATIO_OFFSET = 0.01;

    private Long id;
    private Long sessionId;
    private int width;
    private int height;
    private long fileSize;

    private String fileName;
    private EnableExtension extension;
    private String filePath;

    private CoverImage() {
    }

    public CoverImage(Long sessionId, int width, int height, long fileSize, String fileName, String extension, String filePath) {
        this(0L, sessionId, width, height, fileSize, fileName, EnableExtension.find(extension), filePath);
    }

    public CoverImage(Long id, Long sessionId, int width, int height, long fileSize, String fileName, EnableExtension extension, String filePath) {
        if (sessionId == null) {
            throw new IllegalArgumentException("강의 번호가 누락되어 있습니다");
        }

        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("최소 너비는 " + MIN_WIDTH + " pixel 입니다");
        }

        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("최소 높이는 " + MIN_HEIGHT + " pixel 입니다");
        }

        if (fileSize > MAX_SIZE) {
            throw new IllegalArgumentException("최대 파일 크기를 초과하였습니다");
        }

        if (extension == null) {
            throw new IllegalArgumentException("파일 확장자 정보가 누락되어 있습니다");
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
        this.sessionId = sessionId;
        this.width = width;
        this.height = height;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.extension = extension;
        this.filePath = filePath;
    }

    public static CoverImage of(File file, Long sessionId) throws IOException {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.indexOf(".") + 1);
        long fileSize = file.length();
        String filePath = file.getPath();

        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();

        return new CoverImage(sessionId, width, height, fileSize, fileName, extension, filePath);
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public EnableExtension getExtension() {
        return extension;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        CoverImage that = (CoverImage) other;
        return width == that.width && height == that.height && fileSize == that.fileSize && Objects.equals(id, that.id) && Objects.equals(sessionId, that.sessionId) && Objects.equals(fileName, that.fileName) && extension == that.extension && Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, width, height, fileSize, fileName, extension, filePath);
    }

    @Override
    public String toString() {
        return "CoverImage{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", width=" + width +
                ", height=" + height +
                ", fileSize=" + fileSize +
                ", fileName='" + fileName + '\'' +
                ", extension=" + extension +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
