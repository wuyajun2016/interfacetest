package com.dfire.qa.meal.enums;

public enum Environment {
	
	
	daily("日常环境", "daily"), dev("开发环境", "dev"), prePublish("预发环境", "prePublish"), publish("线上环境", "publish");
	
	private String description;
	private String literal;
	
	
	/**
	 * 根据 literal 返回对应的枚举对象
	 */
	public Environment getEnvironment(String literal){
		
		if(null == literal || literal.isEmpty())
			return null;
		
		for(Environment env: Environment.values()){
			if(env.getLiteral().contains(literal))
				return env;
		}
		
		return null;
	}
	
	private Environment(String description, String literal){
		
		this.setDescription(description);
		this.setLiteral(literal);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}
	
	

}
