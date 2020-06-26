package ivory.database;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import manifold.ext.rt.api.Jailbreak;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDatabaseManager
{
  @Jailbreak DatabaseManager db;

  @BeforeEach
  public void initDb() throws IOException, SQLException
  {
    Path tempDir = Files.createTempDirectory("ivory-test-");
    db = new DatabaseManager("jdbc:h2:" + tempDir+"/ivory-test");

    InputStream resourceStream;

    resourceStream = TestDatabaseManager.class.getResourceAsStream("/database/schema.sql");
    RunScript.execute(db.conn, new InputStreamReader(resourceStream));

    resourceStream = TestDatabaseManager.class.getResourceAsStream("/database/test_population.sql");
    RunScript.execute(db.conn, new InputStreamReader(resourceStream));
  }

  @Test
  public void testRunScriptFromResource() throws IOException, SQLException
  {
    // Test proper execution
    List<Map<String, Object>> results =
      db.runScriptFromResource(getClass(), "/database/test_select_pairs.sql", "Title", "Author");

    Map<Map<String, Object>, Boolean> expected = new HashMap<Map<String, Object>, Boolean>();
    expected.put(Map.of("Title", "The Conquest of Bread", "Author", "Peter Kropotkin"), false);
    expected.put(Map.of("Title", "The Accumulation of Capital", "Author", "Rosa Luxemburg"), false);
    expected.put(Map.of("Title", "The Communist Manifesto", "Author", "Karl Marx"), false);
    expected.put(Map.of("Title", "The Communist Manifesto", "Author", "Friedrich Engels"), false);

    for (Map<String, Object> row : results)
    {
      if (expected.containsKey(row))
        expected.put(row, true);
    }

    for (boolean b : expected.values())
      assertTrue(b);

    // Test access of a non-existent resource
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
