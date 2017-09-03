package com.hyjt.client.mvp.ui.adapter.work;

import java.util.List;

/**
 * @author 赵宇驰
 * @time 2017/7/8  22:37
 * @desc ${TODO}
 */

public class ModuleDataBean {
    private List<ModuleListBean> moduleList;

    public List<ModuleListBean> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleListBean> moduleList) {
        this.moduleList = moduleList;
    }

    public static class ModuleListBean {
        /**
         * name : 公司管理
         * module_parent : [{"name":"公司架构","id":1,"iv":"h-gsjg"},{"name":"公司治理","id":2,"iv":"h-gsjg"},{"name":"部门治理","id":3,"iv":"h-gsjg"},{"name":"政府网站","id":4,"iv":"h-gsjg"},{"name":"公司网站","id":5,"iv":"h-gsjg"}]
         * module_child : [{"name":"环宇论坛","id":6,"iv":"h-gsjg"},{"name":"越级汇报","id":7,"iv":"h-gsjg"},{"name":"献计献策","id":8,"iv":"h-gsjg"},{"name":"政府网站","id":9,"iv":"h-gsjg"}]
         */

        private String name;
        private List<ModuleParentBean> module_parent;
        private List<ModuleChildBean> module_child;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ModuleParentBean> getModule_parent() {
            return module_parent;
        }

        public void setModule_parent(List<ModuleParentBean> module_parent) {
            this.module_parent = module_parent;
        }

        public List<ModuleChildBean> getModule_child() {
            return module_child;
        }

        public void setModule_child(List<ModuleChildBean> module_child) {
            this.module_child = module_child;
        }

        public static class ModuleParentBean {
            /**
             * name : 公司架构
             * id : 1
             * iv : h-gsjg
             */

            private String name;
            private int id;
            private String iv;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIv() {
                return iv;
            }

            public void setIv(String iv) {
                this.iv = iv;
            }
        }

        public static class ModuleChildBean {
            /**
             * name : 环宇论坛
             * id : 6
             * iv : h-gsjg
             */

            private String name;
            private int id;
            private String iv;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIv() {
                return iv;
            }

            public void setIv(String iv) {
                this.iv = iv;
            }
        }
    }
}
