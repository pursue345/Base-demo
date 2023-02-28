package com.donggl.common.sensite.enumStyle;

import java.io.Serializable;

/**
 * @ClassName TestSensiteDto
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 21:55
 * @Version 1.0
 */
public class TestSensiteDto implements Serializable {
    /**
     * name
     */
    @Sensitive(type = SensitiveTypeEnum.NAME)
    private String name;

    /**
     * 身份证号
     */
    @Sensitive(type = SensitiveTypeEnum.ID_NUM)
    private String cardNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
