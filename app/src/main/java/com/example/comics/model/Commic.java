package com.example.comics.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Commic implements Serializable {
    private String id,tenTruyen,tenChap,LinkAnh;

    /*
"tenTruyen":  "   nội dung "

     */


    public Commic() {
    }

    public Commic(JSONObject object) throws JSONException {
        id= object.getString("id");
        tenTruyen = object.getString("tenTruyen"); // truy cập đến " nội dung "
         tenChap = object.getString("tenChap");
         LinkAnh = object.getString("LinkAnh");


    }
    public Commic(String tenTruyen, String tenChap, String linkAnh) {
        this.id = id;
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.LinkAnh = linkAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }
}
