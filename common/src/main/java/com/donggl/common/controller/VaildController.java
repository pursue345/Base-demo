package com.donggl.common.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donggl.common.common.Msg;
import com.donggl.common.common.ResponseUtils;
import com.donggl.common.entity.ProductTopicEntity;
import com.donggl.common.entity.User;
import com.donggl.common.enums.InsertValid;
import com.donggl.common.enums.UpdateValid;
import com.donggl.common.page.PageBeanSearch;
import com.donggl.common.page.PageResponse;
import com.donggl.common.service.IUserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @description: 参数校验控制层
 * @author donggl
 * @date 2022/11/10 14:38
 * @version 1.0
 */
@RestController
@RequestMapping("vaild")
public class VaildController {

    @Autowired
    private IUserService userService;

    @PostMapping("/relationApp")
    @ResponseBody
    public Msg<String> relationApp(@Validated @RequestBody User po) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(User::getCreateAt);
        queryWrapper.eq(StrUtil.isNotBlank(po.getType()), User::getType, po.getType());
        queryWrapper.like(CharSequenceUtil.isNotBlank(po.getName()), User::getName, po.getName());
        List<User> list = userService.list(queryWrapper);
        System.out.println(list.size());
        return Msg.success("成功").build();
    }


    @PostMapping("/insertOta")
    @ResponseBody
    public Msg<Boolean> insertOta(@Validated(InsertValid.class) @RequestBody User po) {
        User user = new User();
        BeanUtils.copyProperties(po, user);
        boolean save = userService.save(user);
        return Msg.success(save).build();
    }

    @PostMapping("insert")
    @ResponseBody
    public Msg<Boolean> insert(@RequestBody @Validated(InsertValid.class) ProductTopicEntity productTopicInfo) {

        System.out.println(productTopicInfo.getMethodType());
        return Msg.success(true).build();
    }

    @PostMapping("delete")
    @ResponseBody
    public Msg<Boolean> delete(@RequestBody User user) {
        boolean b = userService.removeById(user);
        return Msg.success(b).build();
    }


    @PostMapping("update")
    @ResponseBody
    public Msg<Boolean> update(@RequestBody @Validated(UpdateValid.class) ProductTopicEntity productTopicInfo) {
        System.out.println(productTopicInfo.getProductTopicId());
        return Msg.success(true).build();
    }


    @PostMapping("queryUser")
    public Msg<PageResponse<User>> queryUser(@RequestBody @Validated PageBeanSearch<User> pageBeanSearch) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(pageBeanSearch.getQuery().getName()), "name", pageBeanSearch.getQuery().getName());
        Page<User> page = new Page<>(pageBeanSearch.getCurrentPage(), pageBeanSearch.getPageSize());
        IPage<User> page1 = userService.page(page, queryWrapper);
        PageResponse<User> response = new PageResponse<>();
        response.setList(page1);
        return Msg.success(response).build();
    }

    /**
     * 导出
     * @throws IOException
     */
    @PostMapping("export")
    @ResponseBody
    public void export() throws IOException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list = userService.list(queryWrapper);
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, list);
        String fileName = "用户信息列表.xlsx";
        HttpServletResponse response = ResponseUtils.getResponse();
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        workbook.write(response.getOutputStream());
    }
}
