package utils;

public enum FileFormat {

    CSV("csv"), XML("xml");

    private String format;

    FileFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return format;
    }

    public static FileFormat getByName(String name) {

        for(FileFormat format : values()) {
            if(format.getName().equalsIgnoreCase(name)) {
                return format;
            }
        }
        throw new IllegalArgumentException("Illegal file format.");
    }
}
