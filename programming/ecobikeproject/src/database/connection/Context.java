package database.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class Context {

  private static DatabaseType dbtype;

  // get connection of database
  public static Connection getConnection() throws ClassNotFoundException, SQLException {

    return dbtype.getConnection();

  }

  // select database
  public static void selectDataBase(DatabaseType tp) {
    Context.dbtype = tp;
  }
}
