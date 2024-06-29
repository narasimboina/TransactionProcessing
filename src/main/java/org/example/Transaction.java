package org.example;

public class Transaction {
    private String prop1;
    private String prop2;
    private String prop3;
    private String prop4;

    public Transaction(String prop1, String prop2, String prop3, String prop4) {
        this.prop1 = prop1;
        this.prop2 = prop2;
        this.prop3 = prop3;
        this.prop4 = prop4;
    }

    public Transaction() {

    }

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public String getProp3() {
        return prop3;
    }

    public void setProp3(String prop3) {
        this.prop3 = prop3;
    }

    public String getProp4() {
        return prop4;
    }

    public void setProp4(String prop4) {
        this.prop4 = prop4;
    }
    public void setNextProp(String prop){
        if(null == prop1){
            setProp1(prop);
        }else if(null == prop2){
            setProp2(prop);
        }else if(null == prop3){
            setProp3(prop);
        }else if(null == prop4){
            setProp4(prop);
        }
    }
}
