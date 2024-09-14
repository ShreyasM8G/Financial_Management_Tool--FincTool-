package com.example.fmt2.Controllers;

import com.example.fmt2.Entities.Expense;
import com.example.fmt2.Entities.ExpenseDto;
import com.example.fmt2.Entities.Liability;
import com.example.fmt2.Entities.LiabilityDto;
import com.example.fmt2.Repositories.LiabilityRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/liabilities")
public class LiabilityController {

    @Autowired
    private LiabilityRepository liabilityrepo;

    @GetMapping({"","/"})
    public String showLiabilitieslist (Model model, HttpServletRequest request){

        Long uid = (Long) request.getSession().getAttribute("loggedInUserId");
        List<Liability> liabilities = liabilityrepo.findByUid(uid);
        model.addAttribute("liabilities", liabilities);
        return "liabilities/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        LiabilityDto liabilityDto = new LiabilityDto();
        model.addAttribute("liabilityDto",liabilityDto);
        return "liabilities/create";
    }

    @PostMapping("/create")
    public String createLiability(
            @Valid @ModelAttribute LiabilityDto liabilityDto,
            BindingResult result,
            Model model
    )  {

        if (result.hasErrors()) {
            return "liabilities/create";
        }

        // Parse the date string manually
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(liabilityDto.getTharik());
        } catch (ParseException e) {
            // Handle parsing error
            model.addAttribute("dateError", "Invalid date format. Please enter the date in DD-MM-YYYY format.");
            return "liabilities/create";
        }

        // Set the parsed date on the assetDto
        liabilityDto.setTharik(dateFormat.format(parsedDate));

        Liability liability = new Liability();
        liability.setCategory(liabilityDto.getCategory());
        liability.setUid(liabilityDto.getUid());
        liability.setTharik(parsedDate);
        liability.setDescription(liabilityDto.getDescription());
        liability.setAmount(liabilityDto.getAmount());
        liability.setPeriod(liabilityDto.getPeriod());

        liabilityrepo.save(liability);

        return "redirect:/liabilities";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam long lid
    ) {

        try {
            Liability liability = liabilityrepo.findById(lid).orElse(null); // Use findById with Long parameter
            if(liability == null) {

                return "redirect:/liabilities";
            }
            model.addAttribute("liability",liability);

            LiabilityDto liabilityDto = new LiabilityDto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String existingDate = dateFormat.format(liability.getTharik());
            liabilityDto.setTharik(existingDate);

            liabilityDto.setLid(liability.getLid());
            liabilityDto.setUid(liability.getUid());
            liabilityDto.setCategory(liability.getCategory());
            liabilityDto.setAmount(liability.getAmount());
            liabilityDto.setDescription(liability.getDescription());
            liabilityDto.setPeriod(liability.getPeriod());

            model.addAttribute("liabilityDto", liabilityDto);
        }
        catch (Exception ex){
            System.out.println("Exception: "+ ex.getMessage());
            return "redirect:/liabilities";
        }

        return  "liabilities/edit" ;
    }

    @PostMapping("/edit")
    public String updateLiability(
            Model model,
            @RequestParam long lid,
            @Valid @ModelAttribute LiabilityDto liabilityDto,
            BindingResult result
    ) {

        try{
            Liability liability = liabilityrepo.findById(lid).get();
            model.addAttribute("liability", liability);


            if (result.hasErrors()) {
                return "liabilities/edit";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = dateFormat.parse(liabilityDto.getTharik());
            liability.setTharik(parsedDate);

            liability.setLid(liabilityDto.getLid());
            liability.setUid(liabilityDto.getUid());
            liability.setCategory(liabilityDto.getCategory());
            liability.setAmount(liabilityDto.getAmount());
            liability.setDescription(liability.getDescription());
            liability.setPeriod(liabilityDto.getPeriod());

            liabilityrepo.save(liability);

        }
        catch(Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }

        return "redirect:/liabilities";

    }

    @GetMapping("/delete")
    public String deleteLiabilty(
            @RequestParam long lid
    ){
        try {
            Liability liability = liabilityrepo.findById(lid).get();

            liabilityrepo.delete(liability);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/liabilities";
    }

}
