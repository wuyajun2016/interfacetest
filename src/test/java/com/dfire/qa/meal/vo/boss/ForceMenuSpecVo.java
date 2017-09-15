package com.dfire.qa.meal.vo.boss;

import java.io.Serializable;

public class ForceMenuSpecVo implements Serializable {
	
	 private static final long serialVersionUID = -3865208977950677040L;

	    /**
	     * 规格ID
	     */
	    private String specId;

	    /**
	     * 规格名称
	     */
	    private String specName;

	    public String getSpecId() {
	        return specId;
	    }

	    public void setSpecId(String specId) {
	        this.specId = specId;
	    }

	    public String getSpecName() {
	        return specName;
	    }

	    public void setSpecName(String specName) {
	        this.specName = specName;
	    }

}
