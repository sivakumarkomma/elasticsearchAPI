package com.elasticsearch;

import static com.elasticsearch.util.ApplicationConstants.INDEX_NAME;

import java.io.IOException;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.RestHighLevelClient;

public class DeleteIndex {

  public static void main(String args[]) throws IOException {

    RestHighLevelClient highLevelClient = EsConnection.loadClient();

    try {
      DeleteIndexRequest request = new DeleteIndexRequest(INDEX_NAME);
      DeleteIndexResponse deleteIndexResponse = highLevelClient.indices().delete(request);
    } catch (ElasticsearchStatusException ese) {
      ese.getMessage();
    } finally {
      EsConnection.closeClient(highLevelClient);
    }
  }

}
