package nextstep.session.domain;

import nextstep.session.domain.exception.BlankFileNameException;
import nextstep.session.domain.exception.InvalidImageFileSizeException;
import nextstep.session.domain.exception.UnsupportedImageFormatException;
import nextstep.session.domain.exception.InvalidImageSizeException;

import java.util.Arrays;
import java.util.List;

public class CoverImage {
    private static final int MAX_FILE_SIZE = 1024 * 1024; // 1MB
    private static final List<String> SUPPORTED_FORMATS_LIST = Arrays.asList(new String[]{"gif", "jpg", "jpeg", "png", "svg"});

    private final Long id;
    private final String fileName;
    private final String imageFormat;
    private final long fileSize;
    private final int width;
    private final int height;

    public static class Builder {
        private Long id;
        private String fileName;
        private String imageFormat;
        private long fileSize;
        private int width;
        private int height;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder fileName(String fileName) {
            validateFileName(fileName);
            this.fileName = fileName;
            return this;
        }
        public Builder imageFormat(String imageFormat) {
            validateImageFormat(imageFormat);
            this.imageFormat = imageFormat;
            return this;
        }
        public Builder fileSize(long fileSize) {
            validateFileSize(fileSize);
            this.fileSize = fileSize;
            return this;
        }
        public Builder imageSize(int width, int height) {
            validateImageSize(width, height);
            this.width = width;
            this.height = height;
            return this;
        }
        public CoverImage build() {
            validateFileName(fileName);
            validateImageFormat(imageFormat);
            validateFileSize(fileSize);
            validateImageSize(width, height);
            return new CoverImage(this);
        }

    }

    private CoverImage(Builder builder) {
        this.id = builder.id;
        this.fileName = builder.fileName;
        this.imageFormat = builder.imageFormat;
        this.fileSize = builder.fileSize;
        this.width = builder.width;
        this.height = builder.height;
    }

    private static void validateFileName(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new BlankFileNameException(fileName);
        }
    }

    private static void validateImageFormat(String imageFormat) {
        if (imageFormat == null || !SUPPORTED_FORMATS_LIST.contains(imageFormat.toLowerCase())) {
            throw new UnsupportedImageFormatException(imageFormat);
        }
    }

    private static void validateImageSize(int width, int height) {
        if (width < 300 || height < 200) {
            throw new InvalidImageSizeException(width, height);
        }

        double ratio = (double) width / height;
        if (ratio != 1.5) {
            throw new InvalidImageSizeException(width, height);
        }
    }

    private static void validateFileSize(long fileSize) {
        if (fileSize == 0) {
            throw new InvalidImageFileSizeException(fileSize);
        }

        if (fileSize > MAX_FILE_SIZE) { // 1MB
            throw new InvalidImageFileSizeException(fileSize);
        }
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public long getFileSize() {
        return fileSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
