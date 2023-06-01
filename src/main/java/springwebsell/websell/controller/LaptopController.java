package springwebsell.websell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springwebsell.websell.model.Category;
import springwebsell.websell.model.Laptop;
import springwebsell.websell.repository.LaptopRepository;
import springwebsell.websell.service.ICategoryService;
import springwebsell.websell.service.ILaptopService;
import springwebsell.websell.service.impl.StoreService;

import java.security.Principal;
import java.util.List;

@Controller
public class LaptopController {

    @Autowired
    private ILaptopService laptopService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private StoreService storeService;

    @GetMapping("/laptop")
    public String DisplayLaptopPage(Model model){
        List<Laptop> laptops = laptopRepository.findAll();
        model.addAttribute("title", "Laptop page");
        model.addAttribute("laptops", laptops);
        model.addAttribute("size", laptops.size());
        return "laptop/laptop";
    }

    @GetMapping("/laptop/{pageNo}")
    public String laptopPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Page<Laptop> laptops = laptopService.pageLaptops(pageNo);
        model.addAttribute("title", "Laptop page");
        model.addAttribute("size", laptops.getSize());
        model.addAttribute("totalPage", laptops.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("laptops", laptops);
        return "laptop/laptop";
    }

    @GetMapping("/laptop/new")
    public ModelAndView DisplayAddLaptopPage(){
        List<Category> categories = categoryService.findAll();
        return new ModelAndView("laptop/new-laptop")
                .addObject("laptops", new Laptop())
                .addObject("categories", categories);
    }

    @PostMapping("/laptop/new")
    public ModelAndView AddNewLaptop(@Validated Laptop laptop, BindingResult bindingResult){
        if(bindingResult.hasErrors() || laptop.getImages().isEmpty()){
            if(laptop.getImages().isEmpty()){
                bindingResult.rejectValue("images", "MultipartNotEmpty");
            }
            List<Category> categories = categoryService.findAll();
            return new ModelAndView("laptop/new-laptop")
                    .addObject("laptops", laptop)
                    .addObject("categories", categories);
        }

        String filename = storeService.storeFile(laptop.getImages());
        laptop.setFilename(filename);
        laptopService.save(laptop);
        return new ModelAndView("redirect:/laptop/0");
    }

    @GetMapping("/laptop/{id}/edit")
    public ModelAndView DisplayEditLaptopPage(@PathVariable Long id){
        Laptop laptop = laptopService.findById(id);
        List<Category> categories = categoryService.findAll();
        return new ModelAndView("laptop/edit-laptop")
                .addObject("laptops", laptop)
                .addObject("categories", categories);
    }

    @PostMapping("/laptop/{id}/edit")
    public ModelAndView EditInfoLaptop(@PathVariable Long id, @Validated Laptop laptop, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Category> categories = categoryService.findAll();
            return new ModelAndView("laptop/edit-laptop")
                    .addObject("laptops", laptop)
                    .addObject("categories", categories);
        }

        Laptop laptop1 = laptopService.findById(id);
        laptop1.setName(laptop.getName());
        laptop1.setDrive(laptop.getDrive());
        laptop1.setScreen(laptop.getScreen());
        laptop1.setCard(laptop.getCard());
        laptop1.setOperatingSystem(laptop.getOperatingSystem());
        laptop1.setSalePrice(laptop.getSalePrice());
        laptop1.setCurrentQuantity(laptop.getCurrentQuantity());
        laptop1.setCategory(laptop.getCategory());
        laptop1.set_activated(laptop.is_activated());
        laptop1.set_deleted(laptop.is_deleted());

        if(!laptop.getImages().isEmpty()){
            storeService.deleteFile(laptop1.getFilename());
            String filename = storeService.storeFile(laptop.getImages());
            laptop1.setFilename(filename);
        }
        laptopService.save(laptop1);
        return new ModelAndView("redirect:/laptop/0");
    }

    @RequestMapping(value = "/delete-laptop/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String DeleteLaptop(Long id, RedirectAttributes redirectAttributes){
        try{
            laptopService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Delete successfully");
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            redirectAttributes.addFlashAttribute("failed", "Failed delete !!");
        }catch(Exception exception){
            exception.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Error server !!");
        }
        return "redirect:/laptop/0";
    }

    @RequestMapping(value = "/enable-laptop/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String EnableLaptop(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try{
            laptopService.enableById(id);
            redirectAttributes.addFlashAttribute("success", "Enable successfully");
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            redirectAttributes.addFlashAttribute("failed", "Failed Enable  !!");
        }catch(Exception exception){
            exception.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Error server !!");
        }
        return "redirect:/laptop/0";
    }

}
