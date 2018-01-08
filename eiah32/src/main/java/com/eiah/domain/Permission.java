package com.eiah.domain;

import java.io.Serializable;

public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String url;
	private String urlDesc;
	private Integer pid;

	public Permission() {
		super();
	}

	public Permission(Integer id, String url, String urlDesc, Integer pid) {
		super();
		this.id = id;
		this.url = url;
		this.urlDesc = urlDesc;
		this.pid = pid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlDesc() {
		return urlDesc;
	}

	public void setUrlDesc(String urlDesc) {
		this.urlDesc = urlDesc;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
