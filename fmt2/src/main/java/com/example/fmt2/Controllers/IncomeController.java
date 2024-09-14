package com.example.fmt2.Controllers;

import com.example.fmt2.Entities.Income;
import com.example.fmt2.Entities.IncomeDto;
import com.example.fmt2.Repositories.IncomeRepository;
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
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IncomeRepository incomerepo;

    @GetMapping({"","/"})
    public String showIncomeslist (Model model, HttpServletRequest request){

        Long uid = (Long) request.getSession().getAttribute("loggedInUserId");
        List<Income> incomes = incomerepo.findByUid(uid);
        model.addAttribute("incomes", incomes);
        return "incomes/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        IncomeDto incomeDto = new IncomeDto();
        model.addAttribute("incomeDto",incomeDto);
        return "incomes/create";
    }

    @PostMapping("/create")
    public String createIncome(
            @Valid @ModelAttribute IncomeDto incomeDto,
            BindingResult result,
            Model model
    )  {

        if (result.hasErrors()) {
            return "incomes/create";
        }

        // Parse the date string manually
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(incomeDto.getTharik());
        } catch (ParseException e) {
            // Handle parsing error
            model.addAttribute("dateError", "Invalid date format. Please enter the date in DD-MM-YYYY format.");
            return "incomes/create";
        }

        // Set the parsed date on the assetDto
        incomeDto.setTharik(dateFormat.format(parsedDate));

        Income income = new Income();
        income.setCategory(incomeDto.getCategory());
        income.setUid(incomeDto.getUid());
        income.setTharik(parsedDate);
        income.setDescription(incomeDto.getDescription());
        income.setAmt(incomeDto.getAmt());

        incomerepo.save(income);

        return "redirect:/incomes";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam long iid
    ) {

        try {
            Income income = incomerepo.findById(iid).orElse(null); // Use findById with Long parameter
            if(income == null) {

                return "redirect:/incomes";
            }
            model.addAttribute("income",income);

            IncomeDto incomeDto = new IncomeDto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String existingDate = dateFormat.format(income.getTharik());
            incomeDto.setTharik(existingDate);

            incomeDto.setIid(income.getIid());
            incomeDto.setUid(income.getUid());
            incomeDto.setCategory(income.getCategory());
            incomeDto.setAmt(income.getAmt());
            incomeDto.setDescription(income.getDescription());

            model.addAttribute("incomeDto", incomeDto);
        }
        catch (Exception ex){
            System.out.println("Exception: "+ ex.getMessage());
            return "redirect:/incomes";
        }

        return  "incomes/edit" ;
    }

    @PostMapping("/edit")
    public String updateIncome(
            Model model,
            @RequestParam long iid,
            @Valid @ModelAttribute IncomeDto incomeDto,
            BindingResult result
    ) {

        try{
            Income income = incomerepo.findById(iid).get();
            model.addAttribute("income", income);


            if (result.hasErrors()) {
                return "incomes/edit";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = dateFormat.parse(incomeDto.getTharik());
            income.setTharik(parsedDate);

            income.setIid(incomeDto.getIid());
            income.setUid(incomeDto.getUid());
            income.setCategory(incomeDto.getCategory());
            income.setAmt(incomeDto.getAmt());
            income.setDescription(incomeDto.getDescription());


            incomerepo.save(income);

        }
        catch(Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }

        return "redirect:/incomes";

    }

    @GetMapping("/delete")
    public String deleteIncome(
            @RequestParam long iid
    ){
        try {
            Income income = incomerepo.findById(iid).get();

            incomerepo.delete(income);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/incomes";
    }

}
