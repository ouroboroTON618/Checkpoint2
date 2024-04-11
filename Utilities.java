enum Type {
    EQUIPMENT_NAME,
    MEMBER_ID,
    SERIAL_NUM,
}

enum GET_FIELD {
    TYPE,
    SERIAL_NO,
    RENTAL_NO,
    EST_ARR,
    DUE_DATE,
    CUSTOMER_COST
}

enum INFO_LEN {
    MEMBER_ID(4),
    YEAR(4),
    DATE(9),
    SERIAL_NO(6),
    MODEL_NO(3),
    RENTAL_NO(4),
    ORDER_NO(5);

    private final int length;

    INFO_LEN(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}

enum STATEMENT {
    COMPLETED("Are you done with this page? "),
    CONTINUE("Do You Wish to Add More?");

    private final String value;

    STATEMENT(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }

}

enum USER_INFO {
    MEMBER_ID(6308),
    Name("Michael Taylor");

    private final Object value;

    USER_INFO(int length) {
        this.value = length;
    }

    USER_INFO(String name) {
        this.value = name;
    }

    public Object getValue() {
        return value;
    }
}