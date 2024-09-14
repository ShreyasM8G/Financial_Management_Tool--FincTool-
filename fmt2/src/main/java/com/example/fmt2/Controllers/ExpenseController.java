package com.example.fmt2.Controllers;

import com.example.fmt2.Entities.Expense;
import com.example.fmt2.Entities.ExpenseDto;
import com.example.fmt2.Repositories.ExpenseRepository;
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
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenserepo;

    @GetMapping({"","/"})
    public String showExpenseslist (Model model, HttpServletRequest request){

        Long uid = (Long) request.getSession().getAttribute("loggedInUserId");
        List<Expense> expenses = expenserepo.findByUid(uid);
        model.addAttribute("expenses", expenses);
        return "expenses/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ExpenseDto expenseDto = new ExpenseDto();
        model.addAttribute("expenseDto",expenseDto);
        return "expenses/create";
    }

    @PostMapping("/create")
    public String createExpense(
            @Valid @ModelAttribute ExpenseDto expenseDto,
            BindingResult result,
            Model model
    )  {

        if (result.hasErrors()) {
            return "expenses/create";
        }

        // Parse the date string manually
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(expenseDto.getTharik());
        } catch (ParseException e) {
            // Handle parsing error
            model.addAttribute("dateError", "Invalid date format. Please enter the date in DD-MM-YYYY format.");
            return "expenses/create";
        }

        // Set the parsed date on the assetDto
        expenseDto.setTharik(dateFormat.format(parsedDate));

        Expense expense = new Expense();
        expense.setCategory(expenseDto.getCategory());
        expense.setUid(expenseDto.getUid());
        expense.setTharik(parsedDate);
        expense.setTransType(expenseDto.getTransType());
        expense.setDescription(expenseDto.getDescription());
        expense.setAmt(expenseDto.getAmt());

        expenserepo.save(expense);

        return "redirect:/expenses";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam long eid
    ) {

        try {
            Expense expense = expenserepo.findById(eid).orElse(null); // Use findById with Long parameter
            if(expense == null) {

                return "redirect:/expenses";
            }
            model.addAttribute("expense",expense);

            ExpenseDto expenseDto = new ExpenseDto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String existingDate = dateFormat.format(expense.getTharik());
            expenseDto.setTharik(existingDate);

            expenseDto.setEid(expense.getEid());
            expenseDto.setUid(expense.getUid());
            expenseDto.setCategory(expense.getCategory());
            expenseDto.setAmt(expense.getAmt());
            expenseDto.setDescription(expense.getDescription());
            expenseDto.setTransType(expense.getTransType());

            model.addAttribute("expenseDto", expenseDto);
        }
        catch (Exception ex){
            System.out.println("Exception: "+ ex.getMessage());
            return "redirect:/expenses";
        }

        return  "expenses/edit" ;
    }

    @PostMapping("/edit")
    public String updateExpense(
            Model model,
            @RequestParam long eid,
            @Valid @ModelAttribute ExpenseDto expenseDto,
            BindingResult result
    ) {

        try{
            Expense expense = expenserepo.findById(eid).get();
            model.addAttribute("expense", expense);


            if (result.hasErrors()) {
                return "expenses/edit";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = dateFormat.parse(expenseDto.getTharik());
            expense.setTharik(parsedDate);

            expense.setEid(expenseDto.getEid());
            expense.setUid(expenseDto.getUid());
            expense.setCategory(expenseDto.getCategory());
            expense.setAmt(expenseDto.getAmt());
            expense.setDescription(expenseDto.getDescription());
            expense.setTransType(expenseDto.getTransType());

            expenserepo.save(expense);

        }
        catch(Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }

        return "redirect:/expenses";

    }

    @GetMapping("/delete")
    public String deleteExpense(
            @RequestParam long eid
    ){
        try {
            Expense expense = expenserepo.findById(eid).get();

            expenserepo.delete(expense);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/expenses";
    }

}
