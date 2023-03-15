package org.example.description_for_collection;

public enum ColorEye {
    GREEN ("GREEN"),
    YELLOW("YELLOW"),
    BROWN("BROWN");
    private String color;
    ColorEye (String color){
        this.color=color;
    }
    public static String getList(){
        StringBuilder types = new StringBuilder();
        for (ColorEye type : values()) {
            types.append(type.name()).append("; ");
        }
        return types.substring(0, types.length() - 2);
    }
}
