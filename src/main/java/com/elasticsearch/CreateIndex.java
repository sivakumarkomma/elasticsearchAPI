package com.elasticsearch;

import static com.elasticsearch.util.ApplicationConstants.INDEX_NAME;

import java.io.IOException;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestHighLevelClient;

public class CreateIndex {

  public static void main(String args[]) throws IOException {

    RestHighLevelClient highLevelClient = EsConnection.loadClient();

    try {
      CreateIndexRequest request = new CreateIndexRequest(INDEX_NAME);
      CreateIndexResponse createIndexResponse = highLevelClient.indices().create(request);
    } catch (ElasticsearchStatusException ese) {
      ese.getMessage();
    } finally {
      EsConnection.closeClient(highLevelClient);
    }
  }
}
