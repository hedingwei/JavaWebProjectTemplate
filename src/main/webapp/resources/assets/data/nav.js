
[
    {
        name: "首页",
        type: "file",
        picture: "icon-dashboard",
        path: "/f/dashboard.do",
        children: []
    }, {
        name: "基础配置中心",
        picture: "icon-list-alt",
        icon: "/WebManage/assets/js/plugin/tree/img/diy/close.png",
        iconOpen: "/WebManage/assets/js/plugin/tree/img/diy/open.png",
        iconClose: "/WebManage/assets/js/plugin/tree/img/diy/close.png",
        type: "dir",
        path: "/f/",
        children: [
            {
                name: "用户与权限管理",
                type: "file",
                picture: "icon-user",
                path: "/f/um/user/",
                type:"dir",
                children: [
                    {
                        name: "用户管理",
                        type: "file",
                        path: "/f/um/user/list.view.do",
                        children: []
                    },
                    {
                        name: "角色管理",
                        type: "file",
                        path: "/f/um/role/list.view.do",
                        children: []
                    },{
                        name: "区域配置管理",
                        type: "file",
                        path: "/f/um/domain/list.view.do",
                        children: []
                    },{
                        name: "用户操作记录",
                        type: "file",
                        path: "/f/logs/list.view.do",
                        children: []
                    }
                ]
            }
            ,
            {
                name: "长时任务管理中心",
                type: "file",
                path: "/f/lttask/manager/list.view.do",
                children: []
            },

        ]
    }
]


