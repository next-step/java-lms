package nextstep.courses.domain;

import nextstep.courses.domain.session.Session;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SessionImage {
    public static final int MB = 1024 * 1024;
    public static final List<String> IMG_FILE_TYPE = List.of("gif", "jpg", "jpeg", "png", "svg");

    private Long id;
    private long fileSize;
    private String fileType;
    private ImageSize imageSize;
    private Session session;


    public SessionImage() {
    }

    public SessionImage(File file) throws ImageException {
        this.fileSize = validateFileSize(file);
        this.fileType = validateFileType(file);
        this.imageSize = validateImageSize(file);
    }

    public SessionImage(long fileSize, String fileType, ImageSize imageSize) {
        this(null, fileSize, fileType, imageSize, null);
    }

    public SessionImage(Long id, long fileSize, String fileType, ImageSize imageSize, Session session) {
        this.id = id;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.imageSize = imageSize;
        this.session = session;
    }

    private Long validateFileSize(File file) throws ImageException {
        if (file.getTotalSpace() > 1 * MB) {
            throw new ImageException("용량은 1MB를 초과할 수 없습니다.");
        }
        return file.getTotalSpace();
    }

    private String validateFileType(File file) throws ImageException {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!IMG_FILE_TYPE.contains(fileType)) {
            throw new ImageException("이미지 형식 타입이 아닙니다.");
        }
        return fileType;
    }

    private ImageSize validateImageSize(File file) {
        BufferedImage image = null; 
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageSize(image);
    }

    public long getId() {
        return id;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public int getImageWidth() {
        return imageSize.getImageWidth();
    }

    public int getImageHeight() {
        return imageSize.getImageHeight();
    }
}
