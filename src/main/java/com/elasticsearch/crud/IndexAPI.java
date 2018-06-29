package com.elasticsearch.crud;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;

public class IndexAPI {

  public static void main(String args[]) throws IOException {

    RestHighLevelClient highLevelClient = EsConnection.loadClient();

    Map<String, Object> jsonMap = new HashMap<String, Object>();
    jsonMap.put("user", "kimchy");
    jsonMap.put("postDate", new Date());
    jsonMap.put("message", "trying out Elasticsearch");
    IndexRequest indexRequest = new IndexRequest("twitter", "doc", "1")
        .source(jsonMap);
    IndexResponse indexResponse = highLevelClient.index(indexRequest);
    System.out.println("indexResponse  " + indexResponse.getId());
    EsConnection.closeClient(highLevelClient);
  }
}
