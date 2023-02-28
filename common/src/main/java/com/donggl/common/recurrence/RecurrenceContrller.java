package com.donggl.common.recurrence;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName 递归树形结构
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/28 21:28
 * @Version 1.0
 */
@RestController
@RequestMapping("recurrence")
public class RecurrenceContrller {

    @GetMapping(value = "/query")
    public void queryUserList() {
    }

    /**
     * @description:  根据传入list和父节点查询树形结构
     * @author donggl
     * @date 2023/2/28 21:32
     * @version 1.0
     */
    public static ArrayList<TrolleyCarWarehouseRes> getDepartTree(List<TrolleyCarWarehouseRes> allDepart, String warehouseId) {
        ArrayList<TrolleyCarWarehouseRes> roots = new ArrayList<>();
        ArrayList<TrolleyCarWarehouseRes> res = new ArrayList<>();
        //判断warehouseId 是否为空，为空则查询所有数据，不为空则查询该数据下面的子集
        if (!StringUtils.isEmpty(warehouseId)) {
            for (TrolleyCarWarehouseRes departDto : allDepart) {
                //-1表示最高级别的用户
                if (warehouseId.equals(departDto.getWarehouseId())) {
                    roots.add(departDto);
                }
            }
        } else {
            for (TrolleyCarWarehouseRes departDto : allDepart) {
                //-1表示最高级别的用户
                if (departDto.getWarehousePid() == null) {
                    roots.add(departDto);
                }
            }
        }

        //从最高级别用户开始遍历，递归找到该用户的下级用户，将带有下级的最高级用户放入返回结果中
        for (TrolleyCarWarehouseRes userDto : roots) {
            userDto = buildUserTree(allDepart, userDto);
            res.add(userDto);
        }
        return res;
    }

    /**
     * @description: 递归树形结构
     * @author donggl
     * @date 2023/2/28 21:34
     * @version 1.0
     */
    public static TrolleyCarWarehouseRes buildUserTree(List<TrolleyCarWarehouseRes> allDeparts, TrolleyCarWarehouseRes departDTO) {
        List<TrolleyCarWarehouseRes> children = new ArrayList<>();
        //遍历查到的所有用户
        for (TrolleyCarWarehouseRes departDTO1 : allDeparts) {
            //-1代表根节点，无需重复比较
            if (departDTO1.getWarehousePid() == null) {
                continue;
            }
            //当前用户的上级编号和传入的用户编号相等，表示该用户是传入用户的下级用户
            if (departDTO1.getWarehousePid().equals(departDTO.getWarehouseId())) {
                //递归调用，再去寻找该用户的下级用户
                departDTO1 = buildUserTree(allDeparts, departDTO1);
                //当前用户是该用户的一个下级用户，放入children集合内
                children.add(departDTO1);
            }
        }
        //给该用户的children属性赋值，并返回该用户
        departDTO.setChildrenList(children);
        return departDTO;
    }
}
