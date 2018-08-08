package com.mybatis.core.orm.constant;

/**
 * 系统常量
 *
 * @author yuanhuangd
 */
public class SysConstant {

    /**
     * 数据是否有效
     *
     * @author yuanhuangd
     */
    public static class DataStatus {
        public static final int INVALID = 0;// 无效
        public static final int VALID = 1; // 有效
        static final String INVALID_NAME = "无效";// 无效
        static final String VALID_NAME = "有效"; // 有效

        public static String getName(Integer code) {
            String name = "";
            switch (code) {
                case INVALID:
                    name = INVALID_NAME;
                    break;
                case VALID:
                    name = VALID_NAME;
                    break;
            }
            return name;
        }
    }

    /**
     * 是否冻结
     *
     * @author yuanhuangd
     */
    public static class Frozen {
        public static final int NO = 0; // 未冻结
        public static final int YES = 1; // 已冻结
        static final String YES_NAME = "已冻结";
        static final String NO_NAME = "未冻结";

        public static String getName(Integer code) {
            String name = "";
            switch (code) {
                case YES:
                    name = YES_NAME;
                    break;
                case NO:
                    name = NO_NAME;
                    break;
            }
            return name;
        }
    }


    /**
     * 停用，启用
     *
     * @author yuanhuangd
     */
    public static class Enable {
        public static final int NO = 0; // 停用
        public static final int YES = 1; // 启用
        static final String YES_NAME = "启用";
        static final String NO_NAME = "停用";

        public static String getName(Integer code) {
            String name = "";
            switch (code) {
                case YES:
                    name = YES_NAME;
                    break;
                case NO:
                    name = NO_NAME;
                    break;
            }
            return name;
        }
    }

    public static class Locked {
        public static final int NO = 0; // 未锁定
        public static final int YES = 1; // 已锁定
        static final String YES_NAME = "已锁定";
        static final String NO_NAME = "未锁定";

        public static String getName(Integer code) {
            String name = "";
            switch (code) {
                case YES:
                    name = YES_NAME;
                    break;
                case NO:
                    name = NO_NAME;
                    break;
            }
            return name;
        }
    }
}
