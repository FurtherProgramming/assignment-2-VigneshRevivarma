package main;
// enum option for the combobox in login tab.
public enum option {
    Admin , Employee;


    private option() {}

    public String value() {
        return name();
    }

    public static option fromvalue (String v) {
        return valueOf(v);
    }
}

