package crudpractice;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.List;

public class MyElasticsearch {

    public static void main(String[] args) {
        // Step 1: Connect to localhost:9200
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        ElasticsearchClient client = new ElasticsearchClient(
                new RestClientTransport(restClient, new JacksonJsonpMapper())
        );

        try {
            // Step 2: Index a document
            client.index(i -> i
                    .index("users")
                    .id("1")
                    .document(new User("Alice", 25))
            );

            // Step 3: Search for it
            SearchResponse<User> response = client.search(s -> s
                            .index("users")
                            .query(q -> q
                                    .match(m -> m
                                            .field("name")
                                            .query("Alice")
                                    )
                            ),
                    User.class
            );

            List<Hit<User>> hits = response.hits().hits();
            for (Hit<User> hit : hits) {
                System.out.println(hit.source());
            }

        } catch (ElasticsearchException | IOException e) {
            e.printStackTrace();
        }
    }

    // Define your document class
    public record User(String name, int age) {}
}

