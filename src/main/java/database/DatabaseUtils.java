package ivory.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DatabaseUtils
{
  /**
   * Private constructor to prevent instantiation.
   */
  private DatabaseUtils() {}

  public static List<Map<String, Object>> resultSetToMapList(ResultSet rs, String... columnNames)
    throws SQLException
  {
    List<Map<String, Object>> resultMaps = new LinkedList<Map<String, Object>>();

    while (rs.next())
    {
      Map<String, Object> map = new HashMap<String, Object>(columnNames.length);

      for (String col : columnNames)
        map.put(col, rs.getObject(col));

      resultMaps.add(map);
    }

    return resultMaps;
  }
}
