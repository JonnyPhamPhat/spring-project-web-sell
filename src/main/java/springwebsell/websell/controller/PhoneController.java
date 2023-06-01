package springwebsell.websell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springwebsell.websell.model.Category;
import springwebsell.websell.model.SmartPhone;
import springwebsell.websell.service.ICategoryService;
import springwebsell.websell.service.IPhoneService;
import springwebsell.websell.service.impl.StoreService;

import java.security.Principal;
import java.util.List;

@Controller
public class PhoneController {

    @Autowired
    private IPhoneService phoneService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private StoreService storeService;

    @GetMapping("/smartPhone")
    public String DisplayPhonePage(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<SmartPhone> smartPhones = phoneService.findAll();
        model.addAttribute("title", "Smartphone page");
        model.addAttribute("smartPhones", smartPhones);
        model.addAttribute("size", smartPhones.size());
        return "phone/smartPhone";
    }

    @GetMapping("/smartPhone/{pageNo}")
    public String SmartPhonePage(@PathVariable("pageNo") int pageNo, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Page<SmartPhone> smartPhones = phoneService.pagePhone(pageNo);
        model.addAttribute("title", "Smartphone page");
        model.addAttribute("size", smartPhones.getSize());
        model.addAttribute("totalPage", smartPhones.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("smartPhones", smartPhones);
        return "phone/smartPhone";
    }

    @GetMapping("/smartPhone/new")
    public String DisplayAddPhonePage(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("title", "Add smartPhone");
        model.addAttribute("smartPhones", new SmartPhone());
        model.addAttribute("categories", categories);
        return "phone/new-smartPhone";
    }

    @PostMapping("/smartPhone/new")
    public String AddNewSmartPhone(@Validated SmartPhone smartPhone, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors() || smartPhone.getImages().isEmpty()){
            if(smartPhone.getImages().isEmpty()){
                bindingResult.rejectValue("images", "MultipartNotEmpty");
            }
            List<Category> categories = categoryService.findAll();
            model.addAttribute("title", "Add smartPhone");
            model.addAttribute("smartPhones", new SmartPhone());
            model.addAttribute("categories", categories);
            return "phone/new-smartPhone";
        }
        String filename = storeService.storeFile(smartPhone.getImages());
        smartPhone.setFilename(filename);
        phoneService.save(smartPhone);
        return "redirect:/smartPhone/0";
    }

    @GetMapping("/smartPhone/{id}/edit")
    public String DisplayEditPhonePage(@PathVariable("id") Long id, Model model){
        SmartPhone smartPhones = phoneService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("title", "Edit smartPhone");
        model.addAttribute("smartPhones", smartPhones);
        model.addAttribute("categories", categories);
        return "phone/edit-smartPhone";
    }

    @PostMapping("/smartPhone/{id}/edit")
    public String UpdateInfoPhone(@PathVariable("id") Long id, @Validated SmartPhone smartPhones, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Category> categories = categoryService.findAll();
            model.addAttribute("title", "Edit smartPhone");
            model.addAttribute("smartPhones", smartPhones);
            model.addAttribute("categories", categories);
        }
        SmartPhone smartPhone1 = phoneService.findById(id);
        smartPhone1.setName(smartPhones.getName());
        smartPhone1.setOperatingSystem(smartPhones.getOperatingSystem());
        smartPhone1.setChip(smartPhones.getChip());
        smartPhone1.setPin(smartPhones.getPin());
        smartPhone1.setRam(smartPhones.getRam());
        smartPhone1.setSalePrice(smartPhones.getSalePrice());
        smartPhone1.setCurrentQuantity(smartPhones.getCurrentQuantity());
        smartPhone1.setCategory(smartPhones.getCategory());
        smartPhone1.set_activated(smartPhones.is_activated());
        smartPhone1.set_deleted(smartPhones.is_deleted());
        if(!smartPhones.getImages().isEmpty()){
            storeService.deleteFile(smartPhone1.getFilename());
            String filename = storeService.storeFile(smartPhones.getImages());
            smartPhone1.setFilename(filename);
        }
        phoneService.save(smartPhone1);
        return "redirect:/smartPhone/0";
    }

    @RequestMapping(value = "/delete-smartPhone/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String DeleteById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try{
            phoneService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Delete successfully");
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            redirectAttributes.addFlashAttribute("failed", "Failed delete !!");
        }catch(Exception exception){
            exception.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Error server !!");
        }
        return "redirect:/smartPhone/0";
    }

    @RequestMapping(value = "/enable-smartPhone/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String EnableById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try{
            phoneService.enable(id);
            redirectAttributes.addFlashAttribute("success", "Enable successfully");
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            redirectAttributes.addFlashAttribute("failed", "Failed enable !!");
        }catch(Exception exception){
            exception.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Error server !!");
        }
        return "redirect:/smartPhone/0";
    }

}
