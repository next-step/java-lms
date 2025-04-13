package nextstep.courses.domain.image;

public class ImageFileName {
    private final String name;
    private final ImageExtension extension;

    public ImageFileName(String fullFileName) {
        String[] fileNameParts = fullFileName.split("\\.");
        if (fileNameParts.length != 2) {
            throw new IllegalArgumentException("올바른 파일명 형식이 아닙니다.");
        }
        
        this.name = fileNameParts[0];
        this.extension = ImageExtension.from(fileNameParts[1].toLowerCase());
    }

    public String getName() {
        return name;
    }

    public ImageExtension getExtension() {
        return extension;
    }

    public String getFullFileName() {
        return name + "." + extension.getExtension();
    }
} 