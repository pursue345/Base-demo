package com.donggl.common.service.impl;

import com.donggl.common.entity.Type;
import com.donggl.common.mapper.mysql2.TypeMapper;
import com.donggl.common.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author donggl
 * @since 2022-11-23
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

}
