package ivory.database;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager
{
  private Connection conn;

  public DatabaseManager(String connectionUrl) throws SQLException
  {
    JdbcDataSource db = new JdbcDataSource();
    db.setURL(connectionUrl);
    conn = db.getConnection();
  }

  public ResultSet runScriptFromResource(Class<?> cls, String resourcePath) throws SQLException, IOException
  {
    InputStream resourceStream = cls.getResourceAsStream(resourcePath);
    if (resourceStream == null)
      throw new IOException("No such resource '" + resourcePath + "' accessible from " + cls);

    return RunScript.execute(conn, new InputStreamReader(resourceStream));
  }
}
