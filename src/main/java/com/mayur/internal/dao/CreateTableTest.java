package com.mayur.internal.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.Timestamps;
import com.mayur.internal.core.model.Employee;
import com.mayur.internal.core.model.EmployeeMetadata;
import com.mayur.internal.dto.EmployeeDTO;

import java.sql.*;
import java.util.Date;

public class CreateTableTest {

  Connection conn;

  public static void main(String[] args) throws Exception {

    CreateTableTest stub = new CreateTableTest();

    //stub.init("db_file");

    String expr = "CREATE TABLE EMPLOYEE (id INT NOT NULL, name VARCHAR(50), phone DOUBLE,  " +
      "created_on TIMESTAMP, modified_on TIMESTAMP, metadata BLOB, PRIMARY KEY (id))";

    //stub.update(expr);

    //stub.shutdown();

    Employee.Builder dto = Employee.newBuilder();
    dto.setId(10);
    dto.setName("john doe");
    dto.setCreatedOn(Timestamps.fromMillis(System.currentTimeMillis()));
    dto.setModifiedOn(Timestamps.fromMillis(System.currentTimeMillis()));
    dto.setPhoneNumber(6692341234l);
    dto.setMetadata(EmployeeMetadata.newBuilder()
    .setRole("software engineer")
      .setSalary(23.45)
      .setEoj(Timestamps.fromMillis(System.currentTimeMillis()))
      .setReportees(5)
      .build()
    );

    //String str = new ObjectMapper().writeValueAsString(dto);
    //EmployeeDTO e = new ObjectMapper().readValue(str, EmployeeDTO.class);
    //System.out.println(e.getId() + " :: " + e.getName());

    System.out.println(JsonFormat.printer().print(dto.build()));
  }

  private void init(String db_file_name_prefix) throws SQLException, ClassNotFoundException {
    Class.forName("org.hsqldb.jdbc.JDBCDriver");

    // connect to the database.   This will load the db files and start the
    // database if it is not alread running.
    // db_file_name_prefix is used to open or create files that hold the state
    // of the db.
    // It can contain directory names relative to the
    // current working directory
    conn = DriverManager.getConnection("jdbc:hsqldb:"
        + db_file_name_prefix,    // filenames
      "sa",                     // username
      "");                      // password
  }

  public void shutdown() throws SQLException {

    Statement st = conn.createStatement();

    // db writes out to files and performs clean shuts down
    // otherwise there will be an unclean shutdown
    // when program ends
    st.execute("SHUTDOWN");
    conn.close();    // if there are no other open connection
  }

  public synchronized void update(String expression) throws SQLException {

    Statement st = null;

    st = conn.createStatement();    // statements

    int i = st.executeUpdate(expression);    // run the query

    if (i == -1) {
      System.out.println("db error : " + expression);
    }

    st.close();
  }    // void update()

}
