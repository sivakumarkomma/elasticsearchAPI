package com.elasticsearch;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsConnection {

  public static RestHighLevelClient loadClient() {
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(
            new HttpHost("localhost", 9200, "http"),
            new HttpHost("localhost", 9201, "http")));

    return client;
  }

  public static void closeClient(RestHighLevelClient client) throws IOException {
    client.close();
  }
}
