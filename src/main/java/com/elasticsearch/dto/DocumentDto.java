package com.elasticsearch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class DocumentDto {

  private String id;
  private String sa_indexID;
  private String url;
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date sa_date_creation;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date last_modified;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date sa_last_access;
  private long sa_fileSize;
  private boolean sa_isArchived;
  private String sa_allowedSids;
  private String sa_denySids;
  private String sa_shareAllowedSids;
  private String sa_shareDenySids;
  private String sa_MetaData;
  private String vc_mimeType;
  private String vc_immediateParentPath;
  private String vc_rootParentPath;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSa_indexID() {
    return sa_indexID;
  }

  public void setSa_indexID(String sa_indexID) {
    this.sa_indexID = sa_indexID;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getSa_date_creation() {
    return sa_date_creation;
  }

  public void setSa_date_creation(Date sa_date_creation) {
    this.sa_date_creation = sa_date_creation;
  }

  public Date getLast_modified() {
    return last_modified;
  }

  public void setLast_modified(Date last_modified) {
    this.last_modified = last_modified;
  }

  public Date getSa_last_access() {
    return sa_last_access;
  }

  public void setSa_last_access(Date sa_last_access) {
    this.sa_last_access = sa_last_access;
  }

  public long getSa_fileSize() {
    return sa_fileSize;
  }

  public void setSa_fileSize(long sa_fileSize) {
    this.sa_fileSize = sa_fileSize;
  }

  public boolean isSa_isArchived() {
    return sa_isArchived;
  }

  public void setSa_isArchived(boolean sa_isArchived) {
    this.sa_isArchived = sa_isArchived;
  }

  public String getSa_allowedSids() {
    return sa_allowedSids;
  }

  public void setSa_allowedSids(String sa_allowedSids) {
    this.sa_allowedSids = sa_allowedSids;
  }

  public String getSa_denySids() {
    return sa_denySids;
  }

  public void setSa_denySids(String sa_denySids) {
    this.sa_denySids = sa_denySids;
  }

  public String getSa_shareAllowedSids() {
    return sa_shareAllowedSids;
  }

  public void setSa_shareAllowedSids(String sa_shareAllowedSids) {
    this.sa_shareAllowedSids = sa_shareAllowedSids;
  }

  public String getSa_shareDenySids() {
    return sa_shareDenySids;
  }

  public void setSa_shareDenySids(String sa_shareDenySids) {
    this.sa_shareDenySids = sa_shareDenySids;
  }

  public String getSa_MetaData() {
    return sa_MetaData;
  }

  public void setSa_MetaData(String sa_MetaData) {
    this.sa_MetaData = sa_MetaData;
  }

  public String getVc_mimeType() {
    return vc_mimeType;
  }

  public void setVc_mimeType(String vc_mimeType) {
    this.vc_mimeType = vc_mimeType;
  }

  public String getVc_immediateParentPath() {
    return vc_immediateParentPath;
  }

  public void setVc_immediateParentPath(String vc_immediateParentPath) {
    this.vc_immediateParentPath = vc_immediateParentPath;
  }

  public String getVc_rootParentPath() {
    return vc_rootParentPath;
  }

  public void setVc_rootParentPath(String vc_rootParentPath) {
    this.vc_rootParentPath = vc_rootParentPath;
  }

  public DocumentDto() {
  }

  public DocumentDto(String id,
                     String sa_indexID,
                     String url,
                     String name,
                     Date sa_date_creation,
                     Date last_modified,
                     Date sa_last_access,
                     long sa_fileSize,
                     boolean sa_isArchived,
                     String sa_allowedSids,
                     String sa_denySids,
                     String sa_shareAllowedSids,
                     String sa_shareDenySids,
                     String sa_MetaData,
                     String vc_mimeType,
                     String vc_immediateParentPath,
                     String vc_rootParentPath) {
    this.id = id;
    this.sa_indexID = sa_indexID;
    this.url = url;
    this.name = name;
    this.sa_date_creation = sa_date_creation;
    this.last_modified = last_modified;
    this.sa_last_access = sa_last_access;
    this.sa_fileSize = sa_fileSize;
    this.sa_isArchived = sa_isArchived;
    this.sa_allowedSids = sa_allowedSids;
    this.sa_denySids = sa_denySids;
    this.sa_shareAllowedSids = sa_shareAllowedSids;
    this.sa_shareDenySids = sa_shareDenySids;
    this.sa_MetaData = sa_MetaData;
    this.vc_mimeType = vc_mimeType;
    this.vc_immediateParentPath = vc_immediateParentPath;
    this.vc_rootParentPath = vc_rootParentPath;
  }
}
