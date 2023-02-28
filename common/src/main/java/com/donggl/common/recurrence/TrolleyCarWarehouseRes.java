package com.donggl.common.recurrence;

import java.util.List;

/**
 * @ClassName TrolleyCarWarehouseRes
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/28 21:30
 * @Version 1.0
 */
public class TrolleyCarWarehouseRes {

    private String warehouseId;

    private String warehousePid;
    private List<TrolleyCarWarehouseRes> childrenList;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehousePid() {
        return warehousePid;
    }

    public void setWarehousePid(String warehousePid) {
        this.warehousePid = warehousePid;
    }

    public List<TrolleyCarWarehouseRes> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<TrolleyCarWarehouseRes> childrenList) {
        this.childrenList = childrenList;
    }
}
