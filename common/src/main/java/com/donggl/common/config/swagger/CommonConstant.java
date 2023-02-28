package com.donggl.common.config.swagger;

/**
 * @ClassName CommonConstant
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 22:51
 * @Version 1.0
 */
public class CommonConstant {
    public static final String TUID = "tuid";

    /**
     * dubbo请求超时时间
     */
    public static final int DUBBO_TIMEOUT = 1000 * 60 * 2;

    /**
     * 管理端用户信息（request.getHeader）
     */
    public static final String ADMIN_USER_ID = "x-vcs-userid";
    public static final String ADMIN_USERNAME = "x-vcs-username";
    public static final String ADMIN_USER_FULL_NAME = "x-vcs-userfullname";
    public static final String ADMIN_TENANT_ID = "x-vcs-tenantid";
    public static final String ADMIN_TENANT_CODE = "x-vcs-tenant-code";
    public static final String TENANT_ID = "TENANT_ID";

    /**
     * 默认排序规则
     */
//    public static final List<OrderItem> DEFAULT_ORDERS = Collections.singletonList(new OrderItem("update_at", false));

}
