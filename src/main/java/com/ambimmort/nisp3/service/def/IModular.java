package com.ambimmort.nisp3.service.def;

/**
 * Created by ylj on 2015/7/14.
 */
public interface IModular {
    public enum Modular {
        UM("用户与权限管理"),CRM("CRM数据管理"),MATERIAL("共享检测"),SYSTEM("系统管理");

        private String name;
        private Modular(String name){
            this.name = name;
        }
        @Override
        public String toString(){
            return this.name;
        }

    }

    public enum SubModular {
        UM_USER("用户管理"),UM_ROLE("角色管理"),UM_AREA("区域配置管理"),CRM_CONF("CRM接口日志"),CRM_EXPORT("CRM数据导入状态"),CRM_COLUMN("CRM数据列设置"),
        MATERIAL_ALERTMODE("提醒方式管理"),MATERIAL_REMINDCONTENT("提醒内容管理"),MATERIAL_USERINFO("用户信息查询"),
        MATERIAL_CONTROLJOURNAL("控制日志管理"),MATERIAL_TESTJOURNAL("检测日志管理"),
        MATERIAL_BUSINESS_POLICY("业务策略管理"),MATERIAL_WRITELIST("白名单管理"),MATERIAL_CONTROLL_QUERY("监控对象管理"),MATERIAL_CONTROLL_TYPE("控制方案管理"),
        SYSTEM_LOGIN("登录"), SYSTEM_EXIT("退出"), SYSTEM_PERSONINFO("个人信息"),SYSTEM_CHANGEPWD("修改密码");
        private String name;
        private SubModular(String name){
            this.name = name;
        }
        @Override
        public String toString(){
            return this.name;
        }
    }

    public enum Action {
        ADD("新增"),UPDATE("更新"),DELETE("删除"),IMPORT("导入"),EXPORT("导出"),UPLOAD("上传"),EXIT("退出"),LOGIN("登录");
        private String name;
        private Action(String name){
            this.name = name;
        }
        @Override
        public String toString(){
            return this.name;
        }
    }

    public enum Result {
        TRUE("成功"),FALSE("失败");
        private String name;
        private Result(String name){
            this.name = name;
        }
        @Override
        public String toString(){
            return this.name;
        }
    }
}
