package ru.magazine.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.magazine.entity.NCObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by petka on 27.11.2016.
 *
 * @author Evgeniy Tupikov
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        model.addAttribute("time", Calendar.getInstance().getTime().toString());
        NCObject ncObject = new NCObject(UUID.randomUUID().toString(), "bomje", UUID.randomUUID().toString(), UUID.randomUUID().toString());
        model.addAttribute(ncObject);
        return new ModelAndView("objectTypes/edit");
    }
}
