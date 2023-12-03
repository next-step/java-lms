package nextstep.courses.domain;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SessionImage {
    public static final int MB = 1024 * 1024;
    public static final List<String> IMG_FILE_TYPE = List.of("gif", "jpg", "jpeg", "png", "svg");
    private long imageSize;
    private String imageType;
    private Integer imageWidth;
    private Integer imageHeight;


    public SessionImage() {
    }

    public SessionImage(File file) {
        this.imageSize = validateFileSize(file);
        this.imageType = validateFileType(file);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    private Long validateFileSize(File file) {
        if (file.getTotalSpace() > 1*MB) {
            throw new IllegalArgumentException();
        }
        return file.getTotalSpace();
    }

    private String validateFileType(File file) {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!IMG_FILE_TYPE.contains(fileType)) {
            throw new IllegalArgumentException();
        }
        return fileType;
    }

//    private
//        try {
//            BufferedImage image = ImageIO.read(file);
//            if (image.getHeight() < 200 || image.getWidth() < 300 ) {
//                throw new IllegalArgumentException();
//            }
//            if ((double) image.getWidth()/image.getHeight() != 1.5) {
//                throw new IllegalArgumentException();
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
