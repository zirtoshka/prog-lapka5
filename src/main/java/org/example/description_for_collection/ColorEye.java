package org.example.description_for_collection;

public enum ColorEye {
    GREEN,
    YELLOW,
    BROWN;
    public static String getList(){
        StringBuilder types = new StringBuilder();
        for (ColorEye type : values()) {
            types.append(type.name()).append("; ");
        }
        return types.substring(0, types.length() - 2);
    }
}
