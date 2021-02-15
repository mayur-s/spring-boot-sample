package com.mayur.internal.dao;


import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.jdbc.core.JdbcTemplate;


public class BaseDAO {

  //private final JdbcTemplate jdbcTemplate;

  protected void init() throws Exception {

    Class.forName("org.hsqldb.jdbc.JDBCDriver");

    // connect to the database.   This will load the db files and start the
    // database if it is not alread running.
    // db_file_name_prefix is used to open or create files that hold the state
    // of the db.
    // It can contain directory names relative to the
    // current working directory
    Connection conn = DriverManager.getConnection("jdbc:hsqldb:"
        + "",    // filenames
      "sa",                     // username
      "");
  }

}
