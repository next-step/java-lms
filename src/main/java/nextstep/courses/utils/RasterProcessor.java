package nextstep.courses.utils;

import nextstep.courses.domain.SessionCover;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RasterProcessor implements ImageProcessor {

    @Override
    public SessionCover processImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            throw new FileNotFoundException("Invalid image file.");
        }

        return new SessionCover(image.getWidth(), image.getHeight(), file.length(), writeRasterImage(image));
    }

    private static byte[] writeRasterImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();

        byte[] imageInByte = outputStream.toByteArray();
        outputStream.close();

        return imageInByte;
    }


}
