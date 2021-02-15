package com.mayur.internal.controller;

import com.mayur.internal.core.model.*;
import com.mayur.internal.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService empSrv;

  @RequestMapping(path = "/hello", method = RequestMethod.GET)
  public String health(@RequestParam(value = "name", defaultValue = "World") String name){
    System.out.println("Healthyeeeee");
    return "Helloooo " + name;
  }

  @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
  public ServiceResponse getEmployee(@PathVariable(value = "id") String id){
    System.out.println("employee: " + id);
    return empSrv.get(Integer.valueOf(id));
  }

  @RequestMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
    MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public @ResponseBody Employee addEmployee(@RequestBody Employee dto){
    System.out.println("employee adding: " + dto.getId());
    empSrv.add(dto);
    return dto;
  }

}
