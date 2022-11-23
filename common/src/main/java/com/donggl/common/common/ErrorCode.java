package com.donggl.common.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author donggl
 * @version 1.0
 * @description: 错误码
 * @date 2022/11/10 14:28
 */
public abstract class ErrorCode {
    /**
     * 模块代码：SYSTEM
     * <p>
     * 模块名称：系统模块
     * <p>
     * 模块描述： 本模块描述整个平台通用的与具体业务不相关的错误码信息
     */
    public static enum SYSTEM implements IErrorCode {
        /**
         * 成功
         */
        SUCCESS(0, "成功"),
        /**
         * 执行失败
         */
        FAILURE(40000, "执行失败"),
        /**
         * 系统异常
         */
        SYSTEM_EXCEPTION(40001, "系统异常"),
        /**
         * 缺少必填参数
         */
        MISSING_PARAM(40005, "缺少必填参数"),
        /**
         * 参数不合法
         */
        INVALID_PARAM(40006, "参数不合法"),
        /**
         * 签名不正确
         */
        INVALID_SIGN(40007, "签名不正确"),
        /**
         * 服务不可用
         */
        SERVICE_UNAVAILABLE(40008, "服务不可用"),
        /**
         * 验证码不正确
         */
        INVALID_VERIFICATION_CODE(40009, "验证码不正确"),
        /**
         * 数据异常
         */
        ABNORMAL_DATA(40010, "数据异常"),
        /**
         * 重复的操作
         */
        DUPLICATE_OPERATION(40011, "重复的操作"),
        /**
         * 数据不存在
         */
        DATA_NOT_EXISTS(40012, "数据不存在"),
        /**
         * 参数校验异常:{0}
         */
        BIZ_CHECK_ERROR(40013, "参数校验异常:{0}"),
        /**
         * 租户信息异常
         */
        TENANT_ERROR(40014, "租户信息异常"),
        /**
         * 数据重复
         */
        DATA_DUPLICATE(40015, "数据重复"),
        /**
         * 业务异常:{0}
         */
        BIZ_ERROR(40016, "业务异常:{0}"),
        /**
         * 文件大小已超出系统限制
         */
        MAX_FILE_SIZE(40017, "文件大小已超出系统限制"),
        ;

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.msg;
        }

        SYSTEM(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private int code;
        private String msg;

    }


    /**
     * 模块代码：MSG
     * <p>
     * 模块名称：消息、指令模块
     * <p>
     * 模块描述： 本模块描述消息、指令业务相关的错误码信息
     */

    public static enum MSG implements IErrorCode {
        /**
         * 消息推送失败
         */
        MESSAGE_PUSH_FAILED(41000, "消息推送失败"),
        /**
         * 指令发送失败
         */
        COMMAND_SEND_FAILED(41001, "指令发送失败"),
        ;

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.msg;
        }

        MSG(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private int code;
        private String msg;

    }

    /**
     * 模块代码：USER
     * <p>
     * 模块名称：用户模块
     * <p>
     * 模块描述： 本模块描述以用户为错误主体的相关错误码信息
     */
    public static enum USER implements IErrorCode {
        /**
         * 用户不存在
         */
        USER_NOT_EXISTS(43000, "用户不存在"),
        /**
         * 用户不存在该车辆
         */
        USER_NOT_OWN_THE_CAR(43001, "用户不存在该车辆"),
        /**
         * 用户无权解除授权车辆
         */
        USER_NOT_HAS_AUTHORIZATION_TO_RELEASE_AUTHORITY(43002, "用户无权解除授权车辆"),
        ;

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.msg;
        }

        USER(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private int code;
        private String msg;
    }


    /**
     * 模块代码：SECURITY
     * <p>
     * 模块名称：安全模块
     * <p>
     * 模块描述： 本模块描述与授权、认证、安全业务相关的错误码信息
     */

    public static enum SECURITY implements IErrorCode {
        /**
         * token错误或过期
         */
        INVALID_TOKEN(46000, "token错误或过期"),
        /**
         * 无权访问该API
         */
        API_ACCESS_DENIED(46001, "无权访问该API"),
        /**
         * 身份验证失败
         */
        IDENTITY_AUTHENTICATE_FAILED(46002, "身份验证失败"),
        /**
         * 无权访问该数据
         */
        DATA_ACCESS_DENIED(46003, "无权访问该数据"),
        /**
         * 无控制权限
         */
        CONTROL_UNAUTHORIZED(46004, "无控制权限"),
        /**
         * 证书不存在
         */
        CERT_NOT_EXISTS(46005, "证书不存在"),
        /**
         * 密钥更新失败
         */
        KEY_UPDATE_FAILED(46006, "密钥更新失败"),
        /**
         * 加密失败
         */
        ENCRYPT_FAILED(46007, "加密失败"),
        /**
         * 解密失败
         */
        DECRYPT_FAILED(46008, "解密失败"),
        /**
         * 用户授权码过期
         */
        OAUTH_CODE_EXPIRED(46009, "用户授权码过期"),
        /**
         * 临时xcode过期
         */
        TEMPLATE_XCODE_EXPIRED(46010, "临时xcode过期"),
        ;

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.msg;
        }

        SECURITY(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private int code;
        private String msg;
    }


    /**
     * 模块代码：OTA
     * <p>
     * 模块名称：OTA模块
     * <p>
     * 模块描述： 本模块描述与OTA业务相关的错误码信息
     */

    public static enum OTA implements IErrorCode {
        /**
         * 预约时间不正确
         */
        INVALID_APPOINTMENT_DATE(47000, "预约时间不正确"),
        /**
         * 重复的预约
         */
        DUPLICATE_APPOINTMENT(47001, "重复的预约"),
        /**
         * 预约不存在
         */
        APPOINTMENT_NOT_EXISTS(47002, "预约不存在"),
        /**
         * 升级记录不存在
         */
        UPGRADE_RECORD_NOT_EXISTS(47003, "升级记录不存在"),
        /**
         * 终端设备未准备就绪
         */
        DEVICE_NOT_READY(47004, "终端设备未准备就绪"),
        /**
         * 终端软硬件信息不完整
         */
        PART_NUMBER_INFO_INCOMPLETE(47005, "终端软硬件信息不完整"),
        /**
         * 软件版本不合法
         */
        ILLEGAL_VERSION(47006, "软件版本不合法"),
        /**
         * 暂无升级信息
         */
        NO_UPDATE_INFO_YET(47007, "暂无升级信息"),
        /**
         * 终端与云端软件不一致
         */
        SOFTWARE_INFO_INCONSISTENCE(47008, "终端与云端软件不一致"),
        ;

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.msg;
        }

        OTA(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private int code;
        private String msg;

    }


    private static Map<Integer, IErrorCode> map;

    static {
        map = new HashMap<>();
        for (IErrorCode code : SYSTEM.values()) {
            map.put(code.getCode(), code);
        }
        for (IErrorCode code : MSG.values()) {
            map.put(code.getCode(), code);
        }
        for (IErrorCode code : USER.values()) {
            map.put(code.getCode(), code);
        }
        for (IErrorCode code : SECURITY.values()) {
            map.put(code.getCode(), code);
        }
        for (IErrorCode code : OTA.values()) {
            map.put(code.getCode(), code);
        }
    }


    /**
     * 根据code获取错误类型
     *
     * @param code 错误码
     * @return
     */

    public static IErrorCode get(int code) {

        return map.get(code);

    }

}