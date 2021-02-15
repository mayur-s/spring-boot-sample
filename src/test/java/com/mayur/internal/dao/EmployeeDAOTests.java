package com.mayur.internal.dao;

import com.mayur.internal.MainApplication;
import com.mayur.internal.MainApplicationTests;
import com.mayur.internal.core.model.Employee;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

public class EmployeeDAOTests {

  @InjectMocks
  private EmployeeDAO obj;

  @Mock
  private JdbcTemplate jdbcTemplate;

  @BeforeTest
  public void init() {
    MockitoAnnotations.initMocks(this);
    Employee emp =
    Employee.newBuilder()
      .setId(2)
      .setName("hello")
      .build();
    Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(),
      Mockito.any(RowMapper.class))).thenReturn(emp);
  }

  @Test
  public void getEmployeeTest() {
    Employee emp = obj.get(2);
    Assert.assertEquals(emp.getId() , 2);
  }

}
