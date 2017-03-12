package ru.magazine.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.magazine.dao.AttributeDAO;
import ru.magazine.dao.DAOFactory;
import ru.magazine.entity.NCAttribute;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by petka on 06.03.2017.
 *
 * @author Evgeniy Tupikov
 */
@Controller
@RequestMapping("/attributes")
public class AttributeController {

    private String LAST_ID = "";

    @RequestMapping(value = "/legal", method = RequestMethod.GET)
    public String indexD(Model model) {
        model.addAttribute("mess", "attributes");
        return "attributes/attributes";
    }

    //View attribute list for object type
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAttribute(@PathVariable("id") String id, Model model) {
        LAST_ID = id;
        List<NCAttribute> ncAttributeTypesList;
        AttributeDAO attributeDAO = DAOFactory.getDaoFactory(DAOFactory.MYSQL).getAttributeDAO();
        try {
            ncAttributeTypesList = attributeDAO.getByObjectType(id);
            model.addAttribute("attrList", ncAttributeTypesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "attributes/attributes";
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
