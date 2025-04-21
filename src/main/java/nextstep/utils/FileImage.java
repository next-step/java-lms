package nextstep.utils;

import javax.imageio.ImageIO;
import java.io.File;

public class FileImage implements Image {
    private final File file;

    public FileImage(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public int width() {
        try {
            return ImageIO.read(file).getWidth();
        } catch (Exception e) {
            throw new IllegalArgumentException("이미지 파일을 읽는 데 실패했습니다.", e);
        }
    }

    @Override
    public int height() {
        try {
            return ImageIO.read(file).getHeight();
        } catch (Exception e) {
            throw new IllegalArgumentException("이미지 파일을 읽는 데 실패했습니다.", e);
        }
    }

    @Override
    public int size() {
        return (int) file.length();
    }

    @Override
    public String extension() {
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf(".");
        if (lastIndexOfDot == -1) {
            throw new IllegalArgumentException("파일 확장자를 찾을 수 없습니다.");
        }
        return fileName.substring(lastIndexOfDot + 1);
    }
}
