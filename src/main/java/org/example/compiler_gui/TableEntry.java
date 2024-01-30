package org.example.compiler_gui;

public class TableEntry {
    private String name;
    private String value;

    public TableEntry(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public TableEntry() {
        this.name = "";
        this.value = "";
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }
}
