package nextstep.image.domain;

import java.util.Objects;

public class Image {

    private Long id;
    private Long sessionId;
    private long size;
    private final int MB = 1024 * 1024;
    private final int MINIMUM_WIDTH = 300;
    private final int MINIMUM_HEIGHT = 200;
    private ImageType imageType;
    private int width;
    private int height;
    private String filePath;
    private String fileName;
    private String extension;


    public Image(int size, ImageType type, int width, int height) {

        this.size = size;
        this.imageType = type;
        this.width = width;
        this.height = height;
    }

    public Image(Long sessionId, int width, int height, long size, String fileName, String extension, String filePath) {
        this(0L, sessionId, width, height, size, fileName, extension, filePath);
    }

    public Image(Long id, Long sessionId, int width, int height, long size, String fileName, String extension, String filePath) {
        if (sessionId == null)
            throw new IllegalArgumentException("강의 번호가 잘못되었습니다");
        imagePixel(width, height);
        imageSize(size);
        imageType(fileName);
        this.id = id;
        this.sessionId = sessionId;
        this.width = width;
        this.height = height;
        this.size = size;
        this.fileName = fileName;
        this.filePath = filePath;
        this.extension = extension;
    }

    private void imageSize(long size) {
        if (size > 1 * MB)
            throw new IllegalArgumentException("이미지 파일 크기가 너무 큽니다");
    }
    private void imagePixel(int width, int height) {
        if (width < MINIMUM_WIDTH || height < MINIMUM_HEIGHT)
            throw new IllegalArgumentException("크기가 맞지 않습니다");
        if (!(width * 2 == height * 3))
            throw new IllegalArgumentException("비율이 맞지 않습니다");
    }

    private void imageType(String type) {
        for (ImageType value : ImageType.values()) {
            if (!value.name().equals(type))
                throw new IllegalArgumentException("잘못된 파일 타입입니다");
        }
    }

    public long getSize() {
        return size;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMB() {
        return MB;
    }

    public int getMINIMUM_WIDTH() {
        return MINIMUM_WIDTH;
    }

    public int getMINIMUM_HEIGHT() {
        return MINIMUM_HEIGHT;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return size == image.size && width == image.width && height == image.height && imageType == image.imageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, MB, imageType, width, height);
    }

    @Override
    public String toString() {
        return "Image{" +
                "size=" + size +
                ", MB=" + MB +
                ", type=" + imageType +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
