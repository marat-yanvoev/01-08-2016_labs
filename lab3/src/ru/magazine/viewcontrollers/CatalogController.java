package ru.magazine.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by petka on 01.12.2016.
 *
 * @author Evgeniy Tupikov
 */
@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @RequestMapping(method = RequestMethod.GET)
    public String viewCatalog() {
        return "catalog";
    }
}
