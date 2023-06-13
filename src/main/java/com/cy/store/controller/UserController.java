package com.cy.store.controller;

import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileSizeException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.controller.ex.FileUploadIOException;
import com.cy.store.pojo.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import com.cy.store.util.State;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping("/reg")
    public JsonResult<Void> reg(@RequestBody User user){

        userService.reg(user);
        return new JsonResult<>(State.OK);
    }

    @PostMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){

        User data = userService.login(username, password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        return new JsonResult<User>(State.OK, data);
    }

    @PutMapping("/change_password")
    public JsonResult<Void> changePassword(String newPassword, String oldPassword, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid ,username, oldPassword, newPassword);
        return new JsonResult<>(State.OK);
    }

    @GetMapping("/get_info")
    public JsonResult<User> getInfo(HttpSession session){
        User user = userService.getByUid(getUidFromSession(session));
        return new JsonResult<User>(State.OK,null, user);
    }

    @PutMapping("/change_info")
    public JsonResult<Void> changeInfo(@RequestBody User user, HttpSession session){
        userService.changeInfo(getUidFromSession(session), getUsernameFromSession(session),user);
        return new JsonResult<>(State.OK);
    }

    /** 限制文件上传大小 */
    public static final int AVATAR_MAX_SIZE = 100*1024*1024;
    /** 限制文件上传类型 */
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add(".jpeg");
        AVATAR_TYPE.add(".png");
        AVATAR_TYPE.add(".bmp");
        AVATAR_TYPE.add(".gif");
    }

    @PutMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file){

        if(file.isEmpty()){
            throw new FileEmptyException("上传文件为空！");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制！");
        }
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(index);
        if(!AVATAR_TYPE.contains(substring)){
            throw new FileTypeException("文件类型不支持！");
        }
        String parent = session.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String filename = UUID.randomUUID().toString().toUpperCase()+substring;
        File dest = new File(dir,filename);
        try {
            file.transferTo(dest);
        }catch(IOException e){
            throw new FileUploadIOException("文件读取异常！");
        }
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload"+filename;
        userService.changeAvatar(uid,avatar,username);
        return new JsonResult<>(State.OK,avatar);
    }


}
