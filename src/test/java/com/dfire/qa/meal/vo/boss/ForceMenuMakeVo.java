package com.dfire.qa.meal.vo.boss;

import java.io.Serializable;

public class ForceMenuMakeVo  implements Serializable {
	
	private static final long serialVersionUID = -8443544155769649331L;

    /**
     * 做法ID
     */
    private String makeId;

    /**
     * 做法名称
     */
    private String makeName;

    public String getMakeId() {
        return makeId;
    }

    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

}
