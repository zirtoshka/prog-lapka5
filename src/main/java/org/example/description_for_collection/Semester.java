package org.example.description_for_collection;

public enum Semester {
    FIRST ("FIRST"),
    SECOND ("SECOND"),
    FIFTH ("FIFTH");
    private String semester;
    Semester(String semester) {
        this.semester=semester;
    }

    public static String getList(){
        StringBuilder types = new StringBuilder();
        for (Semester type : values()) {
            types.append(type.name()).append("; ");
        }
        return types.substring(0, types.length() - 2);
    }

}
