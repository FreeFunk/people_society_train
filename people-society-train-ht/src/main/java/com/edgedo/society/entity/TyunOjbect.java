package com.edgedo.society.entity;

import java.util.Date;

public class TyunOjbect {
    private String fileUrl;
    private Date uploadTime;
    private String companyName;
    private String uploadUserId;
    private String fileSize;
    private String fileETagHeaders;
    private String dataState;

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(String uploadUserId) {
        this.uploadUserId = uploadUserId;
    }


    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileETagHeaders() {
        return fileETagHeaders;
    }

    public void setFileETagHeaders(String fileETagHeaders) {
        this.fileETagHeaders = fileETagHeaders;
    }
}
