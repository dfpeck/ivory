package ivory.database;

import static ivory.database.DatabaseUtils.resultSetToMapList;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DatabaseManager
{
  Connection conn;

  public DatabaseManager(String connectionUrl)
    throws SQLException
  {
    JdbcDataSource db = new JdbcDataSource();
    db.setURL(connectionUrl);
    conn = db.getConnection();
  }

  public void runScriptFromResource(Class<?> cls, String resourcePath)
    throws SQLException, IOException
  {
    doRunScriptFromResource(cls, resourcePath);
  }

  public List<Map<String, Object>> runScriptFromResource(Class<?> cls, String resourcePath, String... columnNames)
    throws SQLException, IOException
  {
    ResultSet rs =  doRunScriptFromResource(cls, resourcePath);
    List<Map<String, Object>> results = resultSetToMapList(rs, columnNames);
    return results;
  }

  private ResultSet doRunScriptFromResource(Class<?> cls, String resourcePath)
    throws SQLException, IOException
  {
    InputStream resourceStream = cls.getResourceAsStream(resourcePath);
    if (resourceStream == null)
      throw new IOException("No such resource '" + resourcePath + "' accessible from " + cls);

    return RunScript.execute(conn, new InputStreamReader(resourceStream));
  }
}
