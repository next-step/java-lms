package nextstep.courses.domain;

import java.util.Set;

public class File {
    private Set<String> allowedFileTypes;
    private float size;
    private String type;

    public File(Set<String> allowedFileTypes, float size, String type) {
        this.allowedFileTypes = allowedFileTypes;
        this.size = size;
        this.type = type;
    }

    public boolean allowedFileType(String type){
        return this.allowedFileTypes.contains(type);
    }

    public float getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}