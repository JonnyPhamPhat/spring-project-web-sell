package springwebsell.websell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springwebsell.websell.model.Laptop;
import springwebsell.websell.model.SmartPhone;
import springwebsell.websell.repository.LaptopRepository;
import springwebsell.websell.repository.PhoneRepository;
import springwebsell.websell.service.ILaptopService;
import springwebsell.websell.service.IPhoneService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ILaptopService laptopService;

    @Autowired
    private IPhoneService phoneService;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private PhoneRepository phoneRepository;

//    @GetMapping("")
//    public String LoginHomePage(Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
//            return "redirect:/login";
//        }
//        return "shophome";
//    }

    @GetMapping("")
    public ModelAndView ViewShopHomePageLaptop(@PageableDefault(size = 8)Pageable pageable){
        Page<Laptop> laptops = laptopRepository.findAll(pageable);
        Page<SmartPhone> smartPhones = phoneRepository.findAll(pageable);
        return new ModelAndView("shopHome/shophome")
                .addObject("laptops", laptops)
                .addObject("smartPhones", smartPhones);
    }

    @GetMapping("/all-product")
    public ModelAndView ViewAllProductPage(){
        return new ModelAndView("shopHome/allProduct");
    }

    @GetMapping("/laptop/{id}")
    public String ViewDetailLaptopPage(@PathVariable("id") Long id, Model model){
        Laptop laptop = laptopService.findById(id);
        Long categoryId = laptop.getCategory().getId();
        List<Laptop> relatedLaptops = laptopService.getRelatedProducts(categoryId);
        model.addAttribute("laptop", laptop);
        model.addAttribute("relatedLaptops", relatedLaptops);
        return "shopHome/detail-shophome-laptop";
    }

    @GetMapping("/smartPhone/{id}")
    public String ViewDetailSmartPhonePage(@PathVariable("id") Long id, Model model){
        SmartPhone smartPhone = phoneService.findById(id);
        Long categoryId = smartPhone.getCategory().getId();
        List<SmartPhone> relatedSmartPhones = phoneService.getRelatedProducts(categoryId);
        model.addAttribute("smartPhone", smartPhone);
        model.addAttribute("relatedSmartPhones", relatedSmartPhones);
        return "shopHome/detail-shophome-smartPhone";
    }

    @GetMapping("/all-laptop")
    public String ViewAllLaptopPage(Model model){
        List<Laptop> allLaptops = laptopService.findAll();
        model.addAttribute("allLaptops", allLaptops);
        return "laptop/all-laptop";
    }

    @GetMapping("/all-smartPhone")
    public String ViewAllSmartPhonePage(Model model){
        List<SmartPhone> allSmartPhones = phoneService.findAll();
        model.addAttribute("allSmartPhones", allSmartPhones);
        return "phone/all-smartPhone";
    }

    @GetMapping("/all-laptop/{pageNo}")
    public String pageAllLaptop(@PathVariable("pageNo") int pageNo, Model model){
        Page<Laptop> pageAllLaptops = laptopService.pageAllLaptops(pageNo);
        model.addAttribute("size", pageAllLaptops.getSize());
        model.addAttribute("totalPage", pageAllLaptops.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("allLaptops", pageAllLaptops);
        return "laptop/all-laptop";
    }

    @GetMapping("/all-smartPhone/{pageNo}")
    public String pageAllSmartPhone(@PathVariable("pageNo") int pageNo, Model model){
        Page<SmartPhone> pageAllSmartPhones = phoneService.pageAllPhone(pageNo);
        model.addAttribute("size", pageAllSmartPhones.getSize());
        model.addAttribute("totalPage", pageAllSmartPhones.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("allSmartPhones", pageAllSmartPhones);
        return "phone/all-smartPhone";
    }



}
