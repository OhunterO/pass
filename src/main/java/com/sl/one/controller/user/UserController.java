package com.sl.one.controller.user;

import com.sl.one.entity.BnPassInfo;
import com.sl.one.entity.PUser;
import com.sl.one.service.dataTable.TableData;
import com.sl.one.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shi on 2017/1/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 初始化
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String init(){
        return "user/list";
    }

    /**
     * 列表显示
     * @param request
     * @return
     */
    @RequestMapping(value = "/dataShow", method = RequestMethod.POST)
    @ResponseBody
    public TableData userDataTableShow(HttpServletRequest request) {
        // 根据画面查询数据
        TableData tableData = userService.list(request);
        return tableData;
    }

    /**
     * 添加跳转
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForward(Model model) {
        model.addAttribute("user",new PUser());
        model.addAttribute("operation", "add");
        return "user/form";
    }

    /**
     * 添加提交
     * @param pUser
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(PUser pUser) {
        userService.insert(pUser);
        return "redirect:/user";
    }

    /**
     * 更新跳转
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateForward(@PathVariable(value = "id") String id,Model model) {
        model.addAttribute("operation", "update");
        model.addAttribute("user",userService.selectById(id));
        return "user/form";
    }

    /**
     * 更新提交
     * @param pUser
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(PUser pUser) {
        userService.update(pUser);
        return "redirect:/user";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable(value = "id") String id) {
        return userService.delete(id) + "";
    }
}
