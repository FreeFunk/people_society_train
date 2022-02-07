package com.edgedo.society.entity;
public class FaceMatchInfo {
    private String id;
    private String face1;
    private String face1Type;
    private String face2;
    private String face2Type;

    public FaceMatchInfo() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFace1() {
        return this.face1;
    }

    public void setFace1(String face1) {
        this.face1 = face1;
    }

    public String getFace2() {
        return this.face2;
    }

    public void setFace2(String face2) {
        this.face2 = face2;
    }

    public String getFace1Type() {
        return this.face1Type;
    }

    public void setFace1Type(String face1Type) {
        this.face1Type = face1Type;
    }

    public String getFace2Type() {
        return this.face2Type;
    }

    public void setFace2Type(String face2Type) {
        this.face2Type = face2Type;
    }

    public static enum FaceTypeEnum {
        LIVE,
        IDCARD,
        WATERMARK,
        CERT,
        INFRARED;

        private FaceTypeEnum() {
        }
    }
}
