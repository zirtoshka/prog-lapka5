package org.example.description_for_collection;

public enum Country {
    VATICAN("VATICAN"),
    THAILAND("THAILAND"),
    NORTH_KOREA("NORTH_KOREA");
    private String country;
    Country(String country){
        this.country=country;
    }
    public static String getList(){
        StringBuilder types = new StringBuilder();
        for (Country type : values()) {
            types.append(type.name()).append("; ");
        }
        return types.substring(0, types.length() - 2);
    }
}
