package com.tentinet.diver.seal.model;

import java.util.Date;

import com.tentinet.diver.common.BaseModel;

public class SealTemplate extends BaseModel {
    private String sealTemplateId;

    private String sealTemplateUrl;

    private String sealTemplateName;

    private Integer sealTemplatePrice;

    private Integer sealTemplateIntegral;

    private Date sealTemplateCreateTime;

    private Date sealTemplateUpdateTime;

    private String sealTemplateStatus;
    
    private String sealTemplateNew;
    
    private String sealTemplateHot;
    
    private String sealTemplatePresentation;
    
    private String sealUserNo;
    
    private String sealTemplateNameEn;
    
    private String sealTemplatePresentationEn;

    public String getSealTemplateId() {
        return sealTemplateId;
    }

    public void setSealTemplateId(String sealTemplateId) {
        this.sealTemplateId = sealTemplateId == null ? null : sealTemplateId.trim();
    }

    public String getSealTemplateUrl() {
        return sealTemplateUrl;
    }

    public void setSealTemplateUrl(String sealTemplateUrl) {
        this.sealTemplateUrl = sealTemplateUrl == null ? null : sealTemplateUrl.trim();
    }

    public String getSealTemplateName() {
        return sealTemplateName;
    }

    public void setSealTemplateName(String sealTemplateName) {
        this.sealTemplateName = sealTemplateName == null ? null : sealTemplateName.trim();
    }

    public Integer getSealTemplatePrice() {
        return sealTemplatePrice;
    }

    public void setSealTemplatePrice(Integer sealTemplatePrice) {
        this.sealTemplatePrice = sealTemplatePrice;
    }

    public Integer getSealTemplateIntegral() {
        return sealTemplateIntegral;
    }

    public void setSealTemplateIntegral(Integer sealTemplateIntegral) {
        this.sealTemplateIntegral = sealTemplateIntegral;
    }

    public Date getSealTemplateCreateTime() {
        return sealTemplateCreateTime;
    }

    public void setSealTemplateCreateTime(Date sealTemplateCreateTime) {
        this.sealTemplateCreateTime = sealTemplateCreateTime;
    }

    public Date getSealTemplateUpdateTime() {
        return sealTemplateUpdateTime;
    }

    public void setSealTemplateUpdateTime(Date sealTemplateUpdateTime) {
        this.sealTemplateUpdateTime = sealTemplateUpdateTime;
    }

    public String getSealTemplateStatus() {
        return sealTemplateStatus;
    }

    public void setSealTemplateStatus(String sealTemplateStatus) {
        this.sealTemplateStatus = sealTemplateStatus == null ? null : sealTemplateStatus.trim();
    }

	public String getSealTemplateNew() {
		return sealTemplateNew;
	}

	public void setSealTemplateNew(String sealTemplateNew) {
		this.sealTemplateNew = sealTemplateNew;
	}

	public String getSealTemplateHot() {
		return sealTemplateHot;
	}

	public void setSealTemplateHot(String sealTemplateHot) {
		this.sealTemplateHot = sealTemplateHot;
	}

	public String getSealTemplatePresentation() {
		return sealTemplatePresentation;
	}

	public void setSealTemplatePresentation(String sealTemplatePresentation) {
		this.sealTemplatePresentation = sealTemplatePresentation;
	}

	public String getSealUserNo() {
		return sealUserNo;
	}

	public void setSealUserNo(String sealUserNo) {
		this.sealUserNo = sealUserNo;
	}

	public String getSealTemplateNameEn() {
		return sealTemplateNameEn;
	}

	public void setSealTemplateNameEn(String sealTemplateNameEn) {
		this.sealTemplateNameEn = sealTemplateNameEn;
	}

	public String getSealTemplatePresentationEn() {
		return sealTemplatePresentationEn;
	}

	public void setSealTemplatePresentationEn(String sealTemplatePresentationEn) {
		this.sealTemplatePresentationEn = sealTemplatePresentationEn;
	}
}