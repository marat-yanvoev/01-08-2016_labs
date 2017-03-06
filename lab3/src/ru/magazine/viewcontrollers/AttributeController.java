package ru.magazine.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by petka on 06.03.2017.
 *
 * @author Evgeniy Tupikov
 */
@Controller
@RequestMapping("/attribute")
public class AttributeController {

    public String LAST_ID = "";

    //View attribute list for object type
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAttribute(@PathVariable("id") String id, Model model) {
        LAST_ID = id;

        return "viewAttribute";
    }

    //View form to add new attribute
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String viewAddForm(Model model) {

        return "addForm";
    }

    //Add new attribute
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAttribute(Model model) {


        return "redirect:/attribute/" + LAST_ID;
    }

    //View edit form attribute
    @RequestMapping(value = "/edit/{idAttr}", method = RequestMethod.GET)
    public String viewEdit(@PathVariable("idAttr") String idAttr, Model model) {


        return "viewEditAttribute";
    }

    //Update attribute in Database
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model) {

        return "redirect:/attribute/" + LAST_ID;
    }

    //Delete attribute
    @RequestMapping(value = "/del/{idAttr}", method = RequestMethod.GET)
    public String delete(@PathVariable("idAttr") String idAttr, Model model) {


        return "redirect:/attribute/" + LAST_ID;
    }

}
