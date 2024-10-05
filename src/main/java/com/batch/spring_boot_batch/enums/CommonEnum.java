package com.batch.spring_boot_batch.enums;

public enum CommonEnum {
    Test_FILE_NAME("persontest.csv"),
    PERSON_TABLE_NAME("person"),
    INSERT_PERSON_QUERY("INSERT INTO " + CommonEnum.PERSON_TABLE_NAME.getKey() + "(id, first_name, last_name, age) VALUES(:id, :firstName, :lastName, :age)"),


    EXECUTION_FLOW_DECIDER("NONE");

    String key;

    public String getKey() {
        return key;
    }

    CommonEnum(String key) {
        this.key = key;
    }
}

class Test {
    public static void main(String[] args) {
        String key = CommonEnum.Test_FILE_NAME.getKey();
        System.out.println(key);
    }
}
