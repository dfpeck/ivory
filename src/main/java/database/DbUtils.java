package ivory.database;

import org.h2.tools.RunScript;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtils
{
  public static ResultSet runScriptFromResource(Class<?> cls, Connection conn, String resourcePath)
    throws SQLException
  {
    InputStream resourceStream = cls.getResourceAsStream(resourcePath);
    if (resourceStream == null)
      throw new NullPointerException("No such resource '" + resourcePath + "' accessible from " + cls);

    return RunScript.execute(conn, new InputStreamReader(resourceStream));
  }
}
