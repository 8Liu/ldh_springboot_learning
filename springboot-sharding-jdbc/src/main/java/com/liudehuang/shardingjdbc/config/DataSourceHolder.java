package com.liudehuang.shardingjdbc.config;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-26
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-26
 * @UpdateRemark:
 * @Version: 1.0
 */
public class DataSourceHolder {

    private static final ThreadLocal<Long> currentMap = new ThreadLocal();

    private Long merchantId;

    public DataSourceHolder(){

    }

    public static Long get(){
        return currentMap.get();
    }

    public static void set(Long merchantId){
        currentMap.set(merchantId);
    }

    public static void clear() {
        currentMap.remove();
    }
}
