package com.example.fmt2.Controllers;

import com.example.fmt2.Entities.Asset;
import com.example.fmt2.Entities.AssetDto;
import com.example.fmt2.Repositories.AssetRepository;
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
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetRepository assetrepo;

    @GetMapping({"","/"})
    public String showAssetslist (Model model, HttpServletRequest request){
        Long uid = (Long) request.getSession().getAttribute("loggedInUserId");
        List<Asset> assets = assetrepo.findByUid(uid);
        model.addAttribute("assets", assets);
        return "assets/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        AssetDto assetDto = new AssetDto();
        model.addAttribute("assetDto",assetDto);
        return "assets/create";
    }

    @PostMapping("/create")
    public String createAsset(
            @Valid @ModelAttribute AssetDto assetDto,
            BindingResult result,
            Model model
    )  {

        if (result.hasErrors()) {
            return "assets/create";
        }

        // Parse the date string manually
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(assetDto.getTharik());
        } catch (ParseException e) {
            // Handle parsing error
            model.addAttribute("dateError", "Invalid date format. Please enter the date in DD-MM-YYYY format.");
            return "assets/create";
        }

        // Set the parsed date on the assetDto
        assetDto.setTharik(dateFormat.format(parsedDate));

        Asset asset = new Asset();
        asset.setCategory(assetDto.getCategory());
        asset.setUid(assetDto.getUid());
        asset.setTharik(parsedDate);
        asset.setValuation(assetDto.getValuation());
        asset.setDescription(assetDto.getDescription());

        assetrepo.save(asset);

        return "redirect:/assets";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam long aid
    ) {

        try {
            Asset asset = assetrepo.findById(aid).orElse(null); // Use findById with Long parameter
            if(asset == null) {
                // Handle case where asset with given ID is not found
                return "redirect:/assets"; // Redirect to assets index page or show an error page
            }
            model.addAttribute("asset",asset);

            AssetDto assetDto = new AssetDto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String existingDate = dateFormat.format(asset.getTharik());
            assetDto.setTharik(existingDate);

            assetDto.setAid(asset.getAid());
            assetDto.setUid(asset.getUid());
            assetDto.setCategory(asset.getCategory());
            assetDto.setValuation(asset.getValuation());
            assetDto.setDescription(asset.getDescription());

            model.addAttribute("assetDto", assetDto);
        }
        catch (Exception ex){
            System.out.println("Exception: "+ ex.getMessage());
            return "redirect:/assets";
        }

        return  "assets/edit" ;
    }

    @PostMapping("/edit")
    public String updateAsset(
            Model model,
            @RequestParam long aid,
            @Valid @ModelAttribute AssetDto assetDto,
            BindingResult result
    ) {

        try{
            Asset asset = assetrepo.findById(aid).get();
            model.addAttribute("asset", asset);


            if (result.hasErrors()) {
                return "assets/edit";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = dateFormat.parse(assetDto.getTharik());
            asset.setTharik(parsedDate);

            asset.setAid(assetDto.getAid());
            asset.setUid(assetDto.getUid());
            asset.setCategory(assetDto.getCategory());
            asset.setValuation(assetDto.getValuation());
            asset.setDescription(assetDto.getDescription());

            assetrepo.save(asset);

        }
        catch(Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }

        return "redirect:/assets";

    }

    @GetMapping("/delete")
    public String deleteAsset(
            @RequestParam long aid
    ){
        try {
            Asset asset = assetrepo.findById(aid).get();

            assetrepo.delete(asset);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/assets";
    }

}
