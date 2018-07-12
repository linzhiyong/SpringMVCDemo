package com.lzy.mvc.demo.controller;

import com.lzy.mvc.demo.util.Utils;
import com.lzy.mvc.demo.entity.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 请求测试类
 *
 * @author linzhiyong
 * @email wflinzhiyong@163.com
 * @time 2018/7/10
 * @desc
 */
@Controller
@RequestMapping("/app")
public class MainController {

    /**
     * 普通GET请求
     *
     * @param username
     * @param password
     * @param signcode
     * @return
     */
    @RequestMapping(value = "/request1", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    @ResponseBody
    public String request1(String username, String password, String signcode) {
        // 处理中文乱码：new String(password.getBytes("iso8859-1"), "utf-8")
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "参数不能为空！";
        }
        return "服务端返回：username=" + username + "&password=" + password;
    }

    /**
     * 普通POST请求
     *
     * @param username
     * @param password
     * @param signcode
     * @param request
     * @return
     */
    @RequestMapping(value = "/request2", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    @ResponseBody
    public String request2(String username, String password, String signcode, HttpServletRequest request) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "参数不能为空！";
        }
        return "服务端返回：username=" + username + "&password=" + password;
    }

    /**
     * 接收普通string
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/request3", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    @ResponseBody
    public String request3(HttpServletRequest request) {
        String content = Utils.readAsChars(request);
        return "服务端返回：content=" + content;
    }

    /**
     * 接收json数据
     *
     * @param loginInfo
     * @return
     */
    @RequestMapping(value = "/request4", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String request4(@RequestBody LoginInfo loginInfo) {
        String content = loginInfo.toString();
        return "服务端返回：LoginInfo=" + content;
    }

    /**
     * 接收普通表单
     *
     * @param loginInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/request5", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ResponseBody
    public String request5(LoginInfo loginInfo, HttpServletRequest request) {
        String content = loginInfo.toString();
        return "服务端返回：LoginInfo=" + content;
    }

    /**
     * 接收混合表单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/request6", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ResponseBody
    public String request6(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map fileMap = multipartRequest.getFileMap();
        MultipartFile file1 = multipartRequest.getFile("file1"); // 通过参数名获取指定文件
        String file1Id = multipartRequest.getParameter("file1Id");
        String fileName = file1.getOriginalFilename();
        return "服务端返回：file size=" + fileMap.size() + "&fileName=" + fileName + "&file1Id=" + file1Id;
    }

    /**
     * 接收单个文件
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/request7", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ResponseBody
    public String request7(@RequestParam("file1") MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        return "服务端返回：fileName=" + fileName + "&fileSize=" + file.getSize();
    }

    /**
     * 接收多个文件
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/request8", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ResponseBody
    public String request8(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map fileMap = multipartRequest.getFileMap();
        MultipartFile file1 = multipartRequest.getFile("file1"); // 通过参数名获取指定文件
        String fileName = file1.getOriginalFilename();
        return "服务端返回：file size=" + fileMap.size() + "&fileName=" + fileName + "&fileSize=" + file1.getSize();
    }

}
