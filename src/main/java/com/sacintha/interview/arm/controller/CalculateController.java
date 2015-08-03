package com.sacintha.interview.arm.controller;

import com.sacintha.interview.arm.service.Calculator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculateController {
    @RequestMapping(value = "/rest/arm/calculate", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String calculateSum(@RequestBody String expression) throws Exception {
        String output = "";
        Calculator calculator = new Calculator();
        output = calculator.calculate(expression);
        return output;

    }
}
