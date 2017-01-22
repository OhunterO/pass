package com.sl.one.controller.bnPassInfo;

import com.sl.one.entity.BnPassInfo;
import com.sl.one.service.bnPassInfo.BnPassInfoService;
import com.sl.one.service.dataTable.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shi on 2017/1/8.
 */
@Controller
@RequestMapping("/bnpassinfo")
public class BnPassInfoController {

    @Autowired
    private BnPassInfoService bnPassInfoService;

    /**
     * 初始化
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String init(Model model) {
        return "bnpassinfo/list";
    }

    /**
     * 添加跳转
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForward(Model model) {
        model.addAttribute("bnPassInfo",new BnPassInfo());
        model.addAttribute("operation", "add");
        return "bnpassinfo/form";
    }

    /**
     * 添加提交
     *
     * @param bnPassInfo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(BnPassInfo bnPassInfo) {
        bnPassInfoService.insert(bnPassInfo);
        return "redirect:/bnpassinfo";
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
        model.addAttribute("bnPassInfo",bnPassInfoService.selectById(id));
        return "bnpassinfo/form";
    }

    /**
     * 更新提交
     * @param bnPassInfo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(BnPassInfo bnPassInfo) {
        bnPassInfoService.update(bnPassInfo);
        return "redirect:/bnpassinfo";
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
        return bnPassInfoService.delete(id) + "";
    }
    
    /**
     * dataTable数据请求
     * @param request
     * @return
     */
    @RequestMapping(value = "/dataShow", method = RequestMethod.POST)
    @ResponseBody
    public TableData userDataTableShow(HttpServletRequest request) {
        // 根据画面查询数据
        TableData tableData = bnPassInfoService.list(request);
        return tableData;
    }

}
