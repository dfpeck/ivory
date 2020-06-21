package ivory.database;

import org.h2.tools.RunScript;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtils
{
  public static ResultSet runScriptFromResource(Class<?> cls, Connection conn, String resourcePath)
    throws SQLException
  {
    try
    {
      return RunScript.execute(conn, new InputStreamReader(cls.getResourceAsStream(resourcePath)));
    }
    catch (NullPointerException e)
    {
      throw new NullPointerException("No such resource '" + resourcePath + "' accessible from " + cls);
    }
  }
}
