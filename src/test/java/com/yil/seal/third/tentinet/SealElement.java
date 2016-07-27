package com.yil.seal.third.tentinet;

import java.util.Date;

public class SealElement {
    private String sealElementId;

    private Integer sealElementTypeId;

    private String sealElementTemplateId;

    private String sealElementLocation;

    private String sealElementUrl;

    private String sealElementFontFamily;

    private String sealElementFontUrl;

    private float sealElementFontSize;

    private String sealElementFontLength;

    private String sealElementFontColor;

    private Date sealElementCreateTime;

    private Date sealElementUpdateTime;

    private String sealElementStatus;

    public String getSealElementId() {
        return sealElementId;
    }

    public void setSealElementId(String sealElementId) {
        this.sealElementId = sealElementId == null ? null : sealElementId.trim();
    }

    public Integer getSealElementTypeId() {
        return sealElementTypeId;
    }

    public void setSealElementTypeId(Integer sealElementTypeId) {
        this.sealElementTypeId = sealElementTypeId;
    }

    public String getSealElementTemplateId() {
        return sealElementTemplateId;
    }

    public void setSealElementTemplateId(String sealElementTemplateId) {
        this.sealElementTemplateId = sealElementTemplateId == null ? null : sealElementTemplateId.trim();
    }

    public String getSealElementLocation() {
        return sealElementLocation;
    }

    public void setSealElementLocation(String sealElementLocation) {
        this.sealElementLocation = sealElementLocation == null ? null : sealElementLocation.trim();
    }

    public String getSealElementUrl() {
        return sealElementUrl;
    }

    public void setSealElementUrl(String sealElementUrl) {
        this.sealElementUrl = sealElementUrl == null ? null : sealElementUrl.trim();
    }

    public String getSealElementFontFamily() {
        return sealElementFontFamily;
    }

    public void setSealElementFontFamily(String sealElementFontFamily) {
        this.sealElementFontFamily = sealElementFontFamily == null ? null : sealElementFontFamily.trim();
    }

    public String getSealElementFontUrl() {
        return sealElementFontUrl;
    }

    public void setSealElementFontUrl(String sealElementFontUrl) {
        this.sealElementFontUrl = sealElementFontUrl == null ? null : sealElementFontUrl.trim();
    }



    public float getSealElementFontSize() {
		return sealElementFontSize;
	}

	public void setSealElementFontSize(float sealElementFontSize) {
		this.sealElementFontSize = sealElementFontSize;
	}

	public String getSealElementFontLength() {
        return sealElementFontLength;
    }

    public void setSealElementFontLength(String sealElementFontLength) {
        this.sealElementFontLength = sealElementFontLength == null ? null : sealElementFontLength.trim();
    }

    public String getSealElementFontColor() {
        return sealElementFontColor;
    }

    public void setSealElementFontColor(String sealElementFontColor) {
        this.sealElementFontColor = sealElementFontColor == null ? null : sealElementFontColor.trim();
    }

    public Date getSealElementCreateTime() {
        return sealElementCreateTime;
    }

    public void setSealElementCreateTime(Date sealElementCreateTime) {
        this.sealElementCreateTime = sealElementCreateTime;
    }

    public Date getSealElementUpdateTime() {
        return sealElementUpdateTime;
    }

    public void setSealElementUpdateTime(Date sealElementUpdateTime) {
        this.sealElementUpdateTime = sealElementUpdateTime;
    }

    public String getSealElementStatus() {
        return sealElementStatus;
    }

    public void setSealElementStatus(String sealElementStatus) {
        this.sealElementStatus = sealElementStatus == null ? null : sealElementStatus.trim();
    }
}
