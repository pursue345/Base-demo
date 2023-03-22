package com.donggl.common.batch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @ClassName CarConditionAddDto
 * @Description TODO
 * @Author donggl
 * @Date 2023/3/22 21:43
 * @Version 1.0
 */

/**
 * 车况写入数据
 */
public class CarConditionAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆id
     */
    private String carId;
    /**
     * 键值对属性（物模型里的参数）
     */
    private HashMap<String, Object> params;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CarConditionAddDto.class.getSimpleName() + "[", "]")
                .add("carId='" + carId + "'")
                .add("params=" + params)
                .toString();
    }
}
