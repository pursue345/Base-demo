package com.donggl.common.enums;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 枚举校验类
 * @author donggl
 * @date 2022/11/10 14:40
 * @version 1.0
 */
public class EnumValidator implements ConstraintValidator<EnumValue, Object> {
   private String[] strValue;
   private int[] intValue;

   @Override
   public void initialize(EnumValue constraint) {
      this.strValue = constraint.strValues();
      this.intValue = constraint.intValues();
   }

   @Override
   public boolean isValid(Object value, ConstraintValidatorContext context) {
      if(value instanceof String){
         for (String s : strValue) {
            if(s.equals(value))
               return true;
         }
      }
      if(value instanceof Integer){
         for (int i : intValue) {
            if(i == ((Integer) value).intValue())
               return true;
         }
      }
      return false;
   }
}
