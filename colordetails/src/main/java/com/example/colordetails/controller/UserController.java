package com.example.colordetails.controller;

import com.example.colordetails.entity.*;
import com.example.colordetails.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private CompanyService companyService;
    //管理员主页跳转
    @GetMapping("adminIndex")
    public String adminIndexPage(){
        return "AdminIndex";
    }
    //品牌商主页跳转
    @GetMapping("brandIndex")
    public String brandIndexPage(){
        return "BrandIndex";
    }
    //借卖商主页跳转
    @GetMapping("SellerIndex")
    public String sellerIndexPage(){
        return "BrandIndex";
    }
    //登录页面
    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        User user = userService.login(username, password);
        if (user == null) {
            redirectAttributes.addFlashAttribute("msgError", "用户名或者是密码错了！");
            return "redirect:/login";
        }
        if (user.getUser_Type().equals("0")) {
            session.setAttribute("user", user);
            return "AdminIndex";
        } else if (user.getUser_Type().equals("1")) {
            session.setAttribute("user", user);
            return "SellerIndex";
        } else if ((user.getUser_Type().equals("2"))) {
            session.setAttribute("user", user);
            return "BrandIndex";
        }
        return "redirect:/login";
    }

    //注册界面
    @GetMapping("register")
    public String register() {
        return "Register";
    }

    //注册操作
    @PostMapping("register")
    public String register(User user,
                           @RequestParam("userType") String userType,
                           RedirectAttributes redirectAttributes) {
        //先查找用户表里的用户名
        User user1 = userService.findUserByUserName(user.getUser_Name());
        //判断用户名是否重复
        if (user1 == null) {
            //用户名不存在可进行注册
            //设置用户类型，按网页radio的值进行存储
            user.setUser_Type(userType);
            //保存用户信息
            User user2 = userService.register(user);
            //判断用户类型为借卖方
            if (userType.equals("1")) {
                //新建一个借卖方对象
                SellerInfo sellerInfo = new SellerInfo();
                //设置注册借卖方的user_id
                sellerInfo.setUser_Id(user.getId());
                //添加字段
                sellerService.addSellerInfo(sellerInfo);

                //新增一个钱包
                Wallet wallet = new Wallet();
                //把当前注册的借卖方user_id存入钱包信息
                wallet.setUser(user2);
                wallet.setMoney(0f);
                //添加字段
                walletService.addWalletByUserId(wallet);

                //新增一个网店
                Store store = new Store();
                //把当前注册的借卖方seller_id存入网店信息
                store.setSellerInfo(sellerInfo);
                //添加字段
                storeService.addStore(store);
            }
            //判断用户类型为品牌商
            else if (userType.equals("2")) {
                //新建一个品牌商对象
                BrandInfo brandInfo = new BrandInfo();
                //设置注册品牌商的user_id
                brandInfo.setUser_Id(user.getId());
                //查找公司
                CompanyInfo companyInfo = companyService.findCompanyInfoById(1);
                //将此品牌商附属于公司1中
                brandInfo.setCompanyInfo(companyInfo);
                //添加字段
                brandService.addBrandInfo(brandInfo);
                //新增一个钱包
                Wallet wallet = new Wallet();
                //把当前注册的借卖方user_id存入钱包信息
                wallet.setUser(user2);
                wallet.setMoney(0f);
                //添加字段
                walletService.addWalletByUserId(wallet);
            }
            return "redirect:/login";
        }
        //用户名存在刷新页面
        redirectAttributes.addFlashAttribute("msgError", "用户名已经存在！");
        return "redirect:/register";
    }

}
