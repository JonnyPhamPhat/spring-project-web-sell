package springwebsell.websell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springwebsell.websell.model.Category;
import springwebsell.websell.service.ICategoryService;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/category")
    public String displayPageCategories(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("size", categories.size());
        model.addAttribute("categoryNew", new Category());
        model.addAttribute("title", "Category");
        return "category/category";
    }

    @PostMapping("/new-category")
    public String addNewCategory(@ModelAttribute("categoryNew") Category category, RedirectAttributes redirectAttributes){
        try{
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            redirectAttributes.addFlashAttribute("failed", "Failed to add because duplicate name!!");
        } catch(Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Error server !!");
        }
        return "redirect:/category";
    }

    @RequestMapping(value = "/findById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Category findById(Long id){
        return categoryService.findById(id);
    }

    @GetMapping("/update-category")
    public String update(@ModelAttribute("categoryEdit") Category category, RedirectAttributes redirectAttributes){
        try{
            categoryService.update(category);
            redirectAttributes.addFlashAttribute("success", "Update successfully");
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            redirectAttributes.addFlashAttribute("failed", "Failed update !!");
        } catch(Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Error server !!");
        }
        return "redirect:/category";
    }

    @RequestMapping(value = "/delete-category", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteCategory(Long id, RedirectAttributes redirectAttributes){
        try{
            categoryService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Delete successfully");
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            redirectAttributes.addFlashAttribute("failed", "Failed delete !!");
        } catch(Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Error server !!");
        }
        return "redirect:/category";
    }

    @RequestMapping(value = "/enable-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enableCategory(Long id, RedirectAttributes redirectAttributes){
        try{
            categoryService.enableById(id);
            redirectAttributes.addFlashAttribute("success", "Enable successfully");
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            redirectAttributes.addFlashAttribute("failed", "Failed enable !!");
        } catch(Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Error server !!");
        }
        return "redirect:/category";
    }
}
