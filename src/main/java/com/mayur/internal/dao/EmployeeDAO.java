package com.mayur.internal.dao;

import com.google.protobuf.util.Timestamps;
import com.mayur.internal.core.model.*;
import com.mayur.internal.dto.EmployeeDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  RowMapper<Employee> rm = (rs, num) -> {
    Employee.Builder dto = Employee.newBuilder();
    dto.setId(rs.getInt("id"));
    dto.setName(rs.getString("name"));
    dto.setPhoneNumber(rs.getLong("phone"));
    dto.setCreatedOn(Timestamps.fromMillis(rs.getTimestamp("created_on").getTime()));
    dto.setModifiedOn(Timestamps.fromMillis(rs.getTimestamp("modified_on").getTime()));
    try {
      byte[] b = IOUtils.toByteArray(rs.getBlob("metadata").getBinaryStream());
      EmployeeMetadata md = EmployeeMetadata.parseFrom(b);
      dto.setMetadata(md);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto.build();
  };

  public int count() {
    return jdbcTemplate
      .queryForObject("select count(*) from EMPLOYEE", Integer.class);
  }

  public List<Employee> getAll() {
    return jdbcTemplate.query("select * from EMPLOYEE", rm);
  }

  public Employee get(int id) {
    System.out.println("idddd : " + id);
    return jdbcTemplate.queryForObject("select * from EMPLOYEE where id = ?", new Object[]{id}, rm);
  }

  public void save(Employee dto) {
    String sql = "INSERT INTO EMPLOYEE(id, name, phone, created_on, modified_on, metadata) VALUES" +
      "(?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, new Object[]{dto.getId(), dto.getName(), dto.getPhoneNumber(),
      new Date(Timestamps.toMillis(dto.getCreatedOn())),
      new Date(Timestamps.toMillis(dto.getModifiedOn())),
      dto.getMetadata().toByteArray()});
  }

}
