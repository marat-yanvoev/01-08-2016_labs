package ru.magazine.viewcontrollers;

import org.hibernate.type.ObjectType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.magazine.dao.AttributeDAO;
import ru.magazine.dao.DAOFactory;
import ru.magazine.dao.ObjectTypeDAO;
import ru.magazine.entity.NCObjectType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by petka on 26.12.2016.
 *
 * @author Evgeniy Tupikov
 */
@Controller
@RequestMapping("/objectTypes")
public class ObjectTypeController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
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
        return "objectTypes";
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

}
