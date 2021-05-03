package com.example.projectassociates.controller;

import com.example.projectassociates.model.Associate;
import com.example.projectassociates.model.Skill;
import com.example.projectassociates.repo.SkillRepository;
import com.example.projectassociates.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/associates")
public class AssociateController {
    private AssociateService associateService;
    private SkillRepository skillRepository;

    @Autowired
    public AssociateController(AssociateService associateService, SkillRepository skillRepository) {
        this.associateService = associateService;
        this.skillRepository = skillRepository;
    }
    @GetMapping("/view")
    public String getById(@RequestParam("associateId") int theId,Model theModel){
        Associate theAssociate = associateService.findById(theId);

        theModel.addAttribute("associates", theAssociate);

        return "associate-form";
    }
   /* @RequestMapping("/view/{id}")
    public String developer(@PathVariable Integer associateId, Model model) {
        model.addAttribute("associate", associateService.findById(associateId));
        model.addAttribute("skills", skillRepository.findAll());
        return "list-associates";
    }*/
    @GetMapping("/list")
    public String getAssociates(Model model, String keyword)
    {
        List<Associate> list=associateService.getAllAssociates();
        if(keyword!=null){
            model.addAttribute("associates", associateService.findByKeyword(keyword));
        }
        else {
            model.addAttribute("associates", list);
        }
        return "list-associates";
    }
   /* @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Associate theAssociate = new Associate();
        Skill theSkill=new Skill();
        theModel.addAttribute("associates", theAssociate);
        theModel.addAttribute("skills",theSkill);
        return "associate-form";
    }*/
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
      Associate theAssociate = new Associate();
        theModel.addAttribute("associates", theAssociate);
        return "associate-form";
    }
    @PostMapping("/save/skills")
    public String saveAssociate(@ModelAttribute("associates") Associate theAssociate, @ModelAttribute Skill theSkill) {

        associateService.createAssociate(theAssociate);
        skillRepository.save(theSkill);
        return "redirect:/associates/list";
    }
    @PostMapping("/save")
    public String saveAssociate(@ModelAttribute("associates") Associate theAssociate) {

        associateService.createAssociate(theAssociate);
        return "redirect:/associates/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("associateId") int theId, Model theModel) {

       Associate theAssociate = associateService.findById(theId);

        theModel.addAttribute("associates", theAssociate);

        return "associate-form";
    }

    @RequestMapping("/delete")
    public String deleteAssociateById(@RequestParam("associateId") int theId){
        associateService.deleteAssociateById(theId);
        return "redirect:/associates/list";
    }


}