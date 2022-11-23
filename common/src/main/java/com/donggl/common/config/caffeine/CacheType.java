package com.donggl.common.config.caffeine;

/**
 * @ClassName CacheType
 * @Description 缓存类型
 * @Author donggl
 * @Date 2022/11/22 14:41
 * @Version 1.0
 */
public enum CacheType {
    /**
     * 默认缓存
     */
    DEFAULT,
    /**
     * 车辆信息缓存  1分钟
     */
    USER_CAR(60,-1,200000),
    /**
     * 车架号与车信息缓存 1h
     */
    VIN_CAR(60*60,-1,200000),

    ;

    /**
     * 默认数量
     */
    public static final int DEFAULT_MAXSIZE = 50000;
    /**
     * 默认最后一次访问缓存后过期时间，单位秒
     */
    public static final int DEFAULT_EXPIRE_AFTER_ACCESS = 60;
    /**
     * 默认写入缓存后，过期时间，单位秒
     */
    public static final int DEFAULT_EXPIRE_AFTER_WRITE = 60;

    /**
     * 默认构造函数
     */
    CacheType() {
        this(DEFAULT_EXPIRE_AFTER_WRITE, DEFAULT_EXPIRE_AFTER_ACCESS, DEFAULT_MAXSIZE);
    }


    /**
     *
     * @param expireAfterWrite 写入后过期时间
     * @param expireAfterAccess 访问后过期时间
     * @param maxSize 数量
     */
    CacheType(int expireAfterWrite, int expireAfterAccess, int maxSize) {
        this.expireAfterWrite = expireAfterWrite;
        this.expireAfterAccess = expireAfterAccess;
        this.maxSize = maxSize;
    }

    /**
     * 最大数量
     */
    private int maxSize;
    /**
     * 写入多久后过期（秒）
     */
    private int expireAfterWrite;
    /**
     * 最后一次访问后多久过期(秒)
     */
    private int expireAfterAccess;

    public int getMaxSize() {
        return maxSize;
    }

    public int getExpireAfterWrite() {
        return expireAfterWrite;
    }

    public int getExpireAfterAccess() {
        return expireAfterAccess;
    }
}
