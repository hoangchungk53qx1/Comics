package com.example.comics.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Chap {
    private String tenChap,ngayDang,id;

    public Chap() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Chap(String tenChap, String ngayDang) {
        this.tenChap = tenChap;
        this.ngayDang = ngayDang;
    }

    public Chap(JSONObject jsonObject) throws JSONException {
        tenChap = jsonObject.getString("tenChap"); // truy cập đến " nội dung "
        ngayDang = jsonObject.getString("ngaynhap");
        id = jsonObject.getString("id");

    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }
}
