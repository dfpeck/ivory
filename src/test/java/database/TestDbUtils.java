package ivory.database;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.h2.jdbcx.JdbcDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDbUtils
{
  @Test
  public void testRunScriptFromResource() throws SQLException, IOException
  {
    Path tempDir = Files.createTempDirectory("ivory-test-");
    JdbcDataSource db = new JdbcDataSource();
    db.setURL("jdbc:h2:" + tempDir+"/ivory-test");
    Connection conn = db.getConnection();

    ResultSet rs;

    DbUtils.runScriptFromResource(TestDbUtils.class, conn, "/database/schema.sql");
    DbUtils.runScriptFromResource(TestDbUtils.class, conn, "/database/test_population.sql");

    boolean nullPointerExceptionCaught = false;
    try
    {
      DbUtils.runScriptFromResource(TestDbUtils.class, conn, "nonsense");
    }
    catch (NullPointerException e)
    {
      nullPointerExceptionCaught = true;
    }
    assertTrue(nullPointerExceptionCaught);
  }
}
