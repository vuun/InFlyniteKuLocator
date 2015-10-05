package com.example.vuun.description;

/**
 * Created by vuun on 10/4/2015.
 */
import java.io.Serializable;

public class ItemDetail implements Serializable {

    private long id;
    private int imgId;
    private String name;
    private String descr;



    public ItemDetail(long id, int imgId, String name, String descr) {
        this.id = id;
        this.imgId = imgId;
        this.name = name;
        this.descr = descr;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }


}