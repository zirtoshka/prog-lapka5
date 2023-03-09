package org.example.description_for_collection;

public enum ColorHair {
    RED,
    BLUE,
    YELLOW;
    public static String getList(){
        StringBuilder types = new StringBuilder();
        for (ColorHair type : values()) {
            types.append(type.name()).append("; ");
        }
        return types.substring(0, types.length() - 2);
    }
}
