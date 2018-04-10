package com.elasticsearch;

import java.io.IOException;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestHighLevelClient;

public class EsLoader {

  public static void main(String args[]) throws IOException {

    RestHighLevelClient highLevelClient = EsConnection.loadClient();

    try{
      CreateIndexRequest request = new CreateIndexRequest("twitter");
      CreateIndexResponse createIndexResponse = highLevelClient.indices().create(request);
    }catch(ElasticsearchStatusException ese) {
      ese.getMessage();
    } finally {
      EsConnection.closeClient(highLevelClient);
    }
  }


}
