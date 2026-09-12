package br.com.ti.enums;

public enum NavegadoresEnums {
    chrome("chrome"),
    firefox("firefox"),
    edge("edge");

    private final String value;

    NavegadoresEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
