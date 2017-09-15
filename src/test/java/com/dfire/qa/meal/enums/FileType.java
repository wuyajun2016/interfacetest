package com.dfire.qa.meal.enums;

public enum FileType {

	image("图片"), audio("音频"), video("视频");
	
	private String description;
	
	private FileType(String description){
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
