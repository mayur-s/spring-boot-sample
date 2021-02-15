package com.mayur.internal.service;

import com.google.protobuf.util.JsonFormat;
import com.mayur.internal.dao.EmployeeDAO;
import com.mayur.internal.core.model.*;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private static Logger log = LoggerFactory.getLogger(EmployeeService.class);

  @Autowired
  private EmployeeDAO dao;

  public ServiceResponse get(Integer id) {

    try {
      Employee emp = dao.get(id);
      //Service
      return
      ServiceResponse.newBuilder()
        .setPayload(emp)
        .setStatus(ServiceResponse.ResponseStatus.SUCCESS)
        .build();
    } catch (Exception e) {
      log.error("unable to get data", e.getMessage());
      return
        ServiceResponse.newBuilder()
        .setStatus(ServiceResponse.ResponseStatus.FAIL)
        .setError(ServiceError.newBuilder()
          .setCode("500")
          .setDescription(e.getMessage())
          .build())
        .build();
    }
  }

  public void add(Employee dto) {
    dao.save(dto);
  }

  public void deleteById(Integer id) {

  }

}
