package com.mysystem.common;

public enum FundTypeEnum {

    TOTAL_INPUT(0, "总收入"),
    TOTAL_OUTPUT(1, "总支出"),
    TARGET_MONEY(2, "目标金额");

    private Integer type;

    private String descrition;

    FundTypeEnum(Integer type,String descrition){
        this.type = type;
        this.descrition = descrition;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

}
