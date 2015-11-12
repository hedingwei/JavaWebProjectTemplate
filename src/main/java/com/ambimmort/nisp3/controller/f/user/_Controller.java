package com.ambimmort.nisp3.controller.f.user;

/**
 * Created by hedingwei on 5/19/15.
 */
//@Controller("/f/user")
//@RequestMapping("/f/user/bakup")
public class _Controller {


//    @Autowired
//    private IUserManagementService userManagementService;
//
//    public IUserManagementService getUserManagementService() {
//        return userManagementService;
//    }
//
//    public void setUserManagementService(IUserManagementService userManagementService) {
//        this.userManagementService = userManagementService;
//    }
//
//    @RequestMapping("list.do")
//    public ModelAndView _list(HttpServletRequest request){
//
//        ModelAndView mv = new ModelAndView("f/um/user/list");
//
//        ShowList list = new ArrayList();
//        try {
//            list = userManagementService.listUsers();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        mv.addObject("list",list);
//
//        return mv;
//    }
//
//    @RequestMapping("add.do")
//    public ModelAndView _add(){
//        ModelAndView mv = new ModelAndView("f/um/user/add");
//        mv.addObject("model",new AddUserFormModel());
//        return mv;
//    }
//
//
//    @RequestMapping("add.action.do")
//    public ModelAndView _add_action(HttpServletRequest request, @Valid @ModelAttribute("model") AddUserFormModel model, BindingResult result){
//
//        ModelAndView adduserModelAndView = new ModelAndView("f/um/user/add");
//
//
//        if(result.hasErrors()){
//            adduserModelAndView.addObject("model", model);
//            return adduserModelAndView;
//        }else{
//            try {
//                boolean isAlright = true;
//                if(userManagementService.existUser(model.getUsername())){
//                    result.rejectValue("username","user.exist","该用户账号已经存在");
//                    isAlright = false;
//                }
//
//                if(!model.getPassword().equals(model.getPassword_validate())){
//                    result.rejectValue("password","user.password.error","两次密码输入不正确");
//                    isAlright = false;
//                }
//
//                if(isAlright){
//                    userManagementService.addUser(model);
//                } else{
//                    return adduserModelAndView;
//                }
//
//            } catch (Throwable e) {
//                return adduserModelAndView;
//            }
////            request.getServletContext().getContextPath()+"/f/user/list";
//            return _list(request);
//        }
//
//    }
//
//
//    @RequestMapping("update.do")
//    public ModelAndView _update(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("f/um/user/view");
//        return mv;
//    }
//
//    @RequestMapping("view.do")
//    public ModelAndView _view(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("f/um/user/view");
//        return mv;
//    }
//
//    @RequestMapping("delete.do")
//    public ModelAndView _delete(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("forward:f/user/list");
//        return mv;
//    }
//
//    @RequestMapping("edit.do")
//    public ModelAndView _edit(HttpServletRequest request,@RequestParam("username")String username){
//        ModelAndView mv = new ModelAndView("f/um/user/edit");
//        return mv;
//    }
//
//    @RequestMapping("edit.action.do")
//    public ModelAndView _edit_action(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("f/um/user/edit");
//        return mv;
//    }
//
//    @RequestMapping("logs.do")
//    public ModelAndView _logs(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("f/um/user/logs");
//        return mv;
//    }

}
