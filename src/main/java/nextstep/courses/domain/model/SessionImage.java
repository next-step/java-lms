package nextstep.courses.domain.model;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SessionImage extends BaseEntity {
    private static final int MAX_FILE_SIZE_BYTES = 1024 * 1024;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double WIDTH_HEIGHT_RATIO = 1.5;
    private final String path;
    private final byte[] file;

    public SessionImage(String path, int width, int height, byte[] file) {
        super();
        validateSize(file);
        validateExtension(path);
        validateDimensions(width, height);
        this.path = path;
        this.file = file;
    }

    public SessionImage(String path, Blob blob) throws SQLException, IOException {
        this(path, toByteArray(blob));
    }

    public SessionImage(String path, byte[] file) {
        super();
        this.path = path;
        this.file = file;
    }

    private static byte[] toByteArray(Blob blob) throws IOException, SQLException {
        byte[] file = null;
        if (blob != null) {
            file = blob.getBinaryStream().readAllBytes();
        }
        return file;
    }

    private static void validateSize(byte[] file) {
        if (file.length > MAX_FILE_SIZE_BYTES)
            throw new IllegalArgumentException("File size should not exceed 1mb");
    }

    private static void validateExtension(String path) {
        if (ImageExtension.notExist(extractExtension(path)))
            throw new IllegalArgumentException("File extension does not match");
    }

    private static String extractExtension(String path) {
        return path.substring(path.lastIndexOf(".") + 1).toLowerCase();
    }

    private static void validateDimensions(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT || calculateRatio(width, height) != WIDTH_HEIGHT_RATIO)
            throw new IllegalArgumentException("Image width and height must be greater than or equal to 300x200 and 3: 2 ratio");
    }

    private static double calculateRatio(int width, int height) {
        return (double) width / height;
    }

    public String getPath() {
        return path;
    }

    public byte[] getFile() {
        return file;
    }

    public Map<String, Object> getParameters(Long sessionId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("session_id", sessionId);
        parameters.put("image_path", path);
        parameters.put("image_file", file);
        parameters.put("created_at", getCreatedAt());
        parameters.put("updated_at", getUpdatedAt());
        return parameters;
    }

}
