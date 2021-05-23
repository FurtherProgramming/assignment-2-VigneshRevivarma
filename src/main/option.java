package main;

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

