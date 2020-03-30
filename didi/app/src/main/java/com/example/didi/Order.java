package com.example.didi;



import cn.bmob.v3.BmobObject;

public class Order extends BmobObject{
    private String chufa;
    private String mudi;
    public String getChufa() {
        return chufa;
    }
    public void setChufa(String chufa) {
        this.chufa = chufa;
    }
    public String getMudi() {
        return mudi;
    }
    public void setMudi(String mudi) {
        this.mudi = mudi;
    }

}
