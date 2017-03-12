package ru.magazine.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.magazine.dao.DAOFactory;
import ru.magazine.dao.ObjectTypeDAO;
import ru.magazine.entity.NCObjectType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petka on 26.12.2016.
 *
 * @author Evgeniy Tupikov
 */
@Controller
@RequestMapping(value = "/objectTypes")
public class ObjectTypeController {

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        List<NCObjectType> objectTypeList = new ArrayList<NCObjectType>();
        ObjectTypeDAO objectTypeDAO = DAOFactory.getDaoFactory(DAOFactory.MYSQL).getObjectTypeDAO();
        try {
            System.out.println("getAll...");
            objectTypeList = objectTypeDAO.getAll();
            model.addAttribute("type_list", objectTypeList);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("info", e.toString());
        }
        System.out.println("view...");
        return "objectTypes/objectTypes";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sendPost(
                @RequestParam String name,
                @RequestParam String parentId,
                Model model
            ) {
        ObjectTypeDAO objectTypeDAO = DAOFactory.getDaoFactory(DAOFactory.MYSQL).getObjectTypeDAO();
        parentId = parentId == "null" ? null : parentId;
        objectTypeDAO.insert(new NCObjectType("", name, parentId));
        return "redirect:/objectTypes";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String parentId,
            Model model) {

        ObjectTypeDAO objectTypeDAO = DAOFactory.getDaoFactory(DAOFactory.MYSQL).getObjectTypeDAO();
        NCObjectType ncObjectType = new NCObjectType();
        ncObjectType.setId(id);
        ncObjectType.setName(name);
        ncObjectType.setParentId(parentId);
        objectTypeDAO.update(ncObjectType);

        return "redirect:/objectTypes";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editType(@PathVariable("id") String id, Model model) {
//        ObjectTypeDAO objectTypeDAO = DAOFactory.getDaoFactory(DAOFactory.MYSQL).getObjectTypeDAO();
//        NCObjectType ncObjectType = new NCObjectType();
//        List<NCObjectType> objectTypeList = new ArrayList<NCObjectType>();
//        try {
//            ncObjectType = objectTypeDAO.getById(id);
//            objectTypeList = objectTypeDAO.getAll();
//            model.addAttribute("type_list", objectTypeList);
//            model.addAttribute("singleType", ncObjectType);
//            System.out.println("edit" + ncObjectType.getName());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        System.out.println("edit");
        model.addAttribute("id", id);

        return "objectTypes/edit";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, Model model) {
        ObjectTypeDAO objectTypeDAO = DAOFactory.getDaoFactory(DAOFactory.MYSQL).getObjectTypeDAO();
        NCObjectType ncObjectType = new NCObjectType();
        ncObjectType.setId(id);
        objectTypeDAO.delete(ncObjectType);
        model.addAttribute("message", "delete: " + id);
        return "redirect:/objectTypes";
    }

}
