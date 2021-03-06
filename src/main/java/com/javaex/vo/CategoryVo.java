package com.javaex.vo;

public class CategoryVo {

    private int cateNo;
    private String id;
    private String cateName;
    private String description;
    private int countPost;
    
    public CategoryVo() {}

	public CategoryVo(int cateNo, String id, String cateName, String description, int countPost) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.countPost = countPost;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCountPost() {
		return countPost;
	}

	public void setCountPost(int countPost) {
		this.countPost = countPost;
	}

	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName + ", description=" + description
				+ ", countPost=" + countPost + "]";
	}

    
}
