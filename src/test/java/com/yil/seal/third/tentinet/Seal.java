package com.yil.seal.third.tentinet;

import java.util.Date;

import com.tentinet.diver.common.BaseModel;

public class Seal extends BaseModel{
/**
*
*/
private static final long serialVersionUID = -3005182166383242222L;

private String sealId;

private String sealUserNo;

private String sealTemplateColorId;

private String sealUrl;

private String sealIsUse;

private Date sealCreateTime;

private Date sealUpdateTime;

private String sealStatus;

private String sealTemplateIsUse;

public String getSealId() {
return sealId;
}

public void setSealId(String sealId) {
this.sealId = sealId == null ? null : sealId.trim();
}

public String getSealUserNo() {
return sealUserNo;
}

public void setSealUserNo(String sealUserNo) {
this.sealUserNo = sealUserNo == null ? null : sealUserNo.trim();
}

public String getSealTemplateColorId() {
return sealTemplateColorId;
}

public void setSealTemplateColorId(String sealTemplateColorId) {
this.sealTemplateColorId = sealTemplateColorId == null ? null : sealTemplateColorId.trim();
}

public String getSealUrl() {
return sealUrl;
}

public void setSealUrl(String sealUrl) {
this.sealUrl = sealUrl == null ? null : sealUrl.trim();
}

public String getSealIsUse() {
return sealIsUse;
}

public void setSealIsUse(String sealIsUse) {
this.sealIsUse = sealIsUse == null ? null : sealIsUse.trim();
}

public Date getSealCreateTime() {
return sealCreateTime;
}

public void setSealCreateTime(Date sealCreateTime) {
this.sealCreateTime = sealCreateTime;
}

public Date getSealUpdateTime() {
return sealUpdateTime;
}

public void setSealUpdateTime(Date sealUpdateTime) {
this.sealUpdateTime = sealUpdateTime;
}

public String getSealStatus() {
return sealStatus;
}

public void setSealStatus(String sealStatus) {
this.sealStatus = sealStatus == null ? null : sealStatus.trim();
}

public String getSealTemplateIsUse() {
return sealTemplateIsUse;
}

public void setSealTemplateIsUse(String sealTemplateIsUse) {
this.sealTemplateIsUse = sealTemplateIsUse;
}
}