package ivory.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabaseManager
{
  static DatabaseManager db;

  @BeforeAll
  public static void setup() throws IOException, SQLException
  {
    Path tempDir = Files.createTempDirectory("ivory-test-");
    db = new DatabaseManager("jdbc:h2:" + tempDir+"/ivory-test");
  }

  @Test
  public void testRunScriptFromResource() throws IOException, SQLException
  {
    db.runScriptFromResource(getClass(), "/database/schema.sql");
    db.runScriptFromResource(getClass(), "/database/test_population.sql");

    boolean exceptionCaught = false;
    try
    {
      db.runScriptFromResource(getClass(), "nonsense");
    }
    catch (IOException e)
    {
      exceptionCaught = true;
    }
    assertTrue(exceptionCaught);
  }
}
