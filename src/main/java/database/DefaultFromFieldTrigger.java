package ivory.database;

import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Trigger to assign default column values from other column values.
 */
public class DefaultFromFieldTrigger implements Trigger
{
  /**
   * Map (row index -> default index)
   */
  Map<Integer, Integer> assignments;

  /**
   * Constructor.
   */
  public DefaultFromFieldTrigger (Map<Integer, Integer> assignmentsInit)
  {
    assignments = assignmentsInit;
  }

  @Override
  public void init(Connection conn,
                   String schemaName,
                   String triggerName,
                   String tableName,
                   boolean before,
                   int type)
    throws SQLException {}

  @Override
  public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException
  {
    assignments.forEach((k, v) -> {
        if (newRow[k] == null)
          newRow[k] = newRow[v];
      });
  }

  @Override
  public void close() throws SQLException {}

  @Override
  public void remove() throws SQLException {}
}
