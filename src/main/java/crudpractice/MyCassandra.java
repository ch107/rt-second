package crudpractice;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

import java.net.InetSocketAddress;

public class MyCassandra {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .withLocalDatacenter("datacenter1")  // default DC for Docker image
                .build()) {

            // Create keyspace
            session.execute("CREATE KEYSPACE IF NOT EXISTS testks " +
                    "WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}");

            // Use keyspace
            session.execute("USE testks");

            // Create table
            session.execute("CREATE TABLE IF NOT EXISTS users (id int PRIMARY KEY, name text)");

            // Insert data
            session.execute("INSERT INTO users (id, name) VALUES (1, 'Alice')");

            // Select and print
            ResultSet rs = session.execute("SELECT * FROM users");
            for (Row row : rs) {
                System.out.println(row.getInt("id") + ": " + row.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
