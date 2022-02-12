package com.example.demo.Controller;

import com.example.demo.Model.Person;
import com.example.demo.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PersonController {

    private final PersonRepository personRepo;

    @Autowired
    public PersonController(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("person") Person person) {
        personRepo.save(person);

        return "redirect:/view";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("newPerson",new Person());
        return "add";
    }

    @RequestMapping(value="/edit/{id}")
    public String edit(@PathVariable(name = "id") String id, Model m){

        m.addAttribute("currentPerson",personRepo.findById(Long.valueOf(id)));

        return "edit";
    }

    @RequestMapping(value="/delete/{id}")
    public String delete(@PathVariable(name = "id") String id){

        personRepo.deleteById(Long.valueOf(id));
        return "redirect:/view";
    }


}
