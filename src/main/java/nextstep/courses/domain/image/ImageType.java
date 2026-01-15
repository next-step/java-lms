package nextstep.courses.domain.image;

public enum ImageType {
    PNG, JPG, JPEG, SVG, UNKNOWN;

    public static ImageType extract(String fileName) {
        if (!isValidImageFileName(fileName)) {
            return UNKNOWN;
        }

        try {
            String extensionPart = parseExtension(fileName);
            return ImageType.valueOf(extensionPart);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }

    private static boolean isValidImageFileName(String fileName) {
        return fileName != null && !fileName.isBlank() && fileName.contains(".");
    }

    public static String parseExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
    }
}
