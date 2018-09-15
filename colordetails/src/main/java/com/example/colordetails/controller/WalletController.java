package com.example.colordetails.controller;
import com.example.colordetails.entity.Wallet;
import com.example.colordetails.service.SellerService;
import com.example.colordetails.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WalletController {
    @Autowired
    WalletService walletService;
    @Autowired
    SellerService sellerService;
    @GetMapping("WalletPage")
    public String WalletPage(){
        return "SellerWallet";
    }
    //    //查钱包信息，根据user_id
//    @GetMapping("findWalletByUserId/{user_id}")
//    public String findWalletByUserId(@PathVariable("user_id")Integer user_id, Model model){
//        Wallet wallet = walletService.findByWalletUserId(user_id);
//        model.addAttribute("wallet",wallet);
//        return "SellerWallet";
//    }
    //根据user_id查钱包信息,查回来的是user_id
    @GetMapping("findWalletByUserId/{id}")
    public String findWalletByUserId(@PathVariable("id") Integer id, Model model) {
        if (walletService.findWalletByUserId(id) == null) {
            Wallet  wallet = new Wallet();
            wallet.getUser().setId(id);
            Wallet wallet1 = walletService.addWalletByUserId(wallet);
            model.addAttribute("list", walletService.findWalletByUserId(wallet1.getId()));
            return "SellerInfo";
        }
        model.addAttribute("list", walletService.findWalletByUserId(id));
        return "SellerWallet";
    }

    //提现功能
    @PostMapping("withdraw")
    public String withdraw(@RequestParam("number") Float number,
                           @RequestParam("userId") Integer userId,
                           @RequestParam("password") String password,
                           RedirectAttributes redirectAttributes) {
        //获取钱包信息
        Wallet wallet = walletService.findWalletByUserId(userId);
        if (wallet.getPay_Password().equals(password)) {
            //计算提现后的金额
            Float reducemoney = wallet.getMoney() - number;
            //判断需要提现金额的数量
            if (reducemoney < 0) {
                redirectAttributes.addFlashAttribute("msg", "钱不够！");
                return "redirect:/findWalletByUserId/" + userId;
            } else {
                //更改钱包金额
                wallet.setMoney(reducemoney);
                //存入数据库
                walletService.updatePayPassword(wallet);
            }
        } else {
            redirectAttributes.addFlashAttribute("msg", "密码错了啊！");
        }
        return "redirect:/findWalletByUserId/" + userId;
    }

    //充值功能
    @PostMapping("transferIn")
    public String transferIn(@RequestParam("number") Float number,
                             @RequestParam("userId") Integer userId,
                             @RequestParam("password") String password,
                             RedirectAttributes redirectAttributes) {
        //获取钱包信息
        Wallet wallet = walletService.findWalletByUserId(userId);
        if (wallet.getPay_Password().equals(password)) {
            //计算充值后的金额
            Float addmoney = wallet.getMoney() + number;
            //修改钱包的金额
            wallet.setMoney(addmoney);
            //存入数据库
            walletService.updatePayPassword(wallet);
        } else {
            redirectAttributes.addFlashAttribute("msg", "密码错了啊！");
        }
        return "redirect:/findWalletByUserId/" + userId;
    }

    //修改密码
    @PostMapping("updatePayPassword")
    public String updatePassword(@RequestParam("password") String string,
                                 @RequestParam("userId") Integer userId) {

        Wallet wallet = walletService.findWalletByUserId(userId);
        String afterPassword = string;
        wallet.setPay_Password(afterPassword);
        walletService.updatePayPassword(wallet);
        return "redirect:/findWalletByUserId/" + userId;
    }

    //----------------------------------------------------------品牌商钱包

    //根据user_id查钱包信息,查回来的是user_id
    @GetMapping("findWalletByUserIdOfBrand/{id}")
    public String findWalletByUserIdOfBrand(@PathVariable("id") Integer id, Model model) {
        if (walletService.findWalletByUserId(id) == null) {
            Wallet wallet = new Wallet();
            wallet.getUser().setId(id);
//            wallet.setUser_Id(id);

            Wallet wallet1 = walletService.addWalletByUserId(wallet);

            model.addAttribute("list", walletService.findWalletByUserId(wallet1.getId()));
            return "BrandInfo";
        }
        model.addAttribute("list", walletService.findWalletByUserId(id));
        return "BrandWallet";
    }

    //提现功能
    @PostMapping("withdrawBrand")
    public String withdrawBrand(@RequestParam("number") Float number,
                                @RequestParam("userId") Integer userId,
                                @RequestParam("password") String password,
                                RedirectAttributes redirectAttributes) {
        //获取钱包信息
        Wallet wallet = walletService.findWalletByUserId(userId);
        if (wallet.getPay_Password().equals(password)) {
            //计算提现后的金额
            Float reducemoney = wallet.getMoney() - number;
            //判断需要提现金额的数量
            if (reducemoney < 0) {
                redirectAttributes.addFlashAttribute("msg", "钱不够！");
                return "redirect:/findWalletByUserIdOfBrand/" + userId;
            } else {
                //更改钱包金额
                wallet.setMoney(reducemoney);
                //存入数据库
                walletService.updatePayPassword(wallet);
            }
        } else {
            redirectAttributes.addFlashAttribute("msg", "密码错了啊！");
        }
        return "redirect:/findWalletByUserIdOfBrand/" + userId;
    }

    //充值功能
    @PostMapping("transferInBrand")
    public String transferInBrand(@RequestParam("number") Float number,
                                  @RequestParam("userId") Integer userId,
                                  @RequestParam("password") String password,
                                  RedirectAttributes redirectAttributes) {
        //获取钱包信息
        Wallet wallet = walletService.findWalletByUserId(userId);
        if (wallet.getPay_Password().equals(password)) {
            //计算充值后的金额
            Float addmoney = wallet.getMoney() + number;
            //修改钱包的金额
            wallet.setMoney(addmoney);
            //存入数据库
            walletService.updatePayPassword(wallet);
        } else {
            redirectAttributes.addFlashAttribute("msg", "密码错了啊！");
        }
        return "redirect:/findWalletByUserIdOfBrand/" + userId;
    }

    //修改密码
    @PostMapping("updateBrandPayPassword")
    public String updateBrandPayPassword(@RequestParam("password") String string,
                                         @RequestParam("userId") Integer userId) {

        Wallet wallet = walletService.findWalletByUserId(userId);
        String afterPassword = string;
        wallet.setPay_Password(afterPassword);
        walletService.updatePayPassword(wallet);
        return "redirect:/findWalletByUserIdOfBrand/" + userId;
    }
}
