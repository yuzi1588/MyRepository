package com.example.colordetails.controller;
import com.example.colordetails.Util.FileUtil;
import com.example.colordetails.entity.BrandInfo;
import com.example.colordetails.entity.CompanyInfo;
import com.example.colordetails.service.BrandService;
import com.example.colordetails.service.CompanyService;
import com.example.colordetails.service.UserService;
import com.example.colordetails.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;
@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private CompanyService companyService;
    //管理员操作
    //查询所有品牌信息
    @GetMapping("findAllBrandInfo")
    public String findAllBrandInfo(Model model){
        List<BrandInfo> brandlist=brandService.findAllBrand();
        model.addAttribute("list",brandlist);
        return "AdminForBrand";
    }
    //根据user_id查品牌信息,传回来的是user_id
    @GetMapping("findBrandInfoById/{id}")
    public String findBrandInfoById(@PathVariable("id") Integer id,Model model){
        if (brandService.findBrandByUserId(id)==null){
            BrandInfo brandInfo=new BrandInfo();
            brandInfo.setCompanyInfo(companyService.findCompanyInfoById(1));
            brandInfo.setUser_Id(id);
            BrandInfo brandInfo1=brandService.addBrandInfo(brandInfo);
            model.addAttribute("list",brandInfo1);
            return "BrandManage";
        }
        model.addAttribute("list",brandService.findBrandByUserId(id));
        return "BrandManage";
    }
    //根据品牌id查品牌信息,传回来的是id
    @GetMapping("findBrandInfoByBrandId/{id}")
    public String findBrandInfoByBrandId(@PathVariable("id") Integer id,Model model){
        model.addAttribute("list",brandService.findBrandById(id));
        return "BrandManage";
    }
    //根据user_id删除品牌信息and登陆帐号信息and钱包信息
    @GetMapping("/deleteBrandInfoById/{id}")
    public String deleteByID(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
        return "redirect:/findAllBrandInfo";
    }

    //管理员修改品牌商信息,id：为品牌商id
    @GetMapping("adminUpdateBrandInfoById/{id}")
    public String adminUpdateBrandInfoById(@PathVariable("id") Integer id,Model model){
        BrandInfo brandInfo=brandService.findBrandById(id);
        model.addAttribute("bo",brandInfo);
        return "BrandUpdateAdmin";
    }
    @PostMapping("adminUpdateBrandInfoById")
    public String adminUpdateBrandInfoById(BrandInfo brandInfo,@RequestParam("file")MultipartFile file){
        String contentType = file.getContentType();
        String fileName= file.getOriginalFilename();
        String filePath = FileUtil.getUpLoadFilePath();
        fileName =System.currentTimeMillis()+fileName;
        try{
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        }catch (Exception e){
            //TODO:handle exception
        }
        Date date =new Date();
        brandInfo.setLogo(fileName);
        brandService.addBrandInfo(brandInfo);
        return "redirect:/findAllBrandInfo";
    }

    //品牌商的更新
    @GetMapping("brandUpdateBrandInfoById/{id}")
    public String brandUpdateBrandInfoById(@PathVariable("id") Integer id,Model model){
        BrandInfo brandInfo=brandService.findBrandById(id);
        model.addAttribute("bo",brandInfo);
        return "BrandUpdate";
    }
    @PostMapping("brandUpdateBrandInfoById")
    //修改&新增 品牌信息
    public String brandUpdateBrandInfoById(BrandInfo brandInfo, @RequestParam("file")MultipartFile file){
            String contentType = file.getContentType();
            String fileName= file.getOriginalFilename();
            String filePath = FileUtil.getUpLoadFilePath();
            fileName =System.currentTimeMillis()+fileName;
            try{
                FileUtil.uploadFile(file.getBytes(),filePath,fileName);
            }catch (Exception e){
                //TODO:handle exception
            }
            Date date =new Date();
            brandInfo.setLogo(fileName);
            brandService.addBrandInfo(brandInfo);
        return "redirect:/findBrandInfoByBrandId/"+brandInfo.getId();
    }
}


