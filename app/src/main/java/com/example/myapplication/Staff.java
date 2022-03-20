package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Staff {
    private long idStaff;
    private String nameStaff;
    private String sexStaff;
    private String positionStaff;
    private Bitmap linkImage;
    public long getIdStaff() {
        return idStaff;
    }
    public void setIdStaff(long idStaff) {
        this.idStaff = idStaff;
    }
    public String getNameStaff() {
        return nameStaff;
    }
    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }
    public String getSexStaff() {
        return sexStaff;
    }
    public void setSexStaff(String sexStaff) {
        this.sexStaff = sexStaff;
    }
    public String getPositionStaff() {
        return positionStaff;
    }
    public void setPositionStaff(String positionStaff) {
        this.positionStaff = positionStaff;
    }
    public Bitmap getLinkImage() {
        return linkImage;
    }
    public void setLinkImage(Bitmap linkImage) {
        this.linkImage = linkImage;
    }
    public Staff(long idStaff, String nameStaff, String sexStaff, String positionStaff, Bitmap linkImage) {
        this.idStaff = idStaff;
        this.nameStaff = nameStaff;
        this.sexStaff = sexStaff;
        this.positionStaff = positionStaff;
        this.linkImage = linkImage;
    }
    public Staff() {
    }
}
