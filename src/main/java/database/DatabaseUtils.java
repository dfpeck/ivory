package ivory.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Collection of utility functions for database interaction.
 */
public class DatabaseUtils
{
  /**
   * Private constructor to prevent instantiation.
   */
  private DatabaseUtils() {}

  /**
   * Convert a result set to a list of maps.
   *
   * Each row will be represented as a map (column name -> value), each of which will be
   * stored in a list.
   *
   * @param rs ResultSet object to convert.
   * @param columnNames Names of the columns to extract from each row.
   *
   * @return List of maps, each representing a row from the ResultSet.
   */
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
