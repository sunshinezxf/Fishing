package fishing.sunshine.controller;

import fishing.sunshine.form.DivisionForm;
import fishing.sunshine.model.division.City;
import fishing.sunshine.model.division.District;
import fishing.sunshine.model.division.Province;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunshine on 1/27/16.
 */
@RequestMapping("/division")
@RestController
public class DivisionController {
    private Logger logger = LoggerFactory.getLogger(DivisionController.class);

    @ResponseBody
    @RequestMapping("/create")
    public String createDivision(DivisionForm divisionForm) {
        Province province = new Province(divisionForm);


        City city = new City(divisionForm);

        District district = new District(divisionForm);

        return "success";
    }
}
