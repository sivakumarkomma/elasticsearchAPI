package com.elasticsearch.crud;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;

public class EsUpdateAPI {

  public static void main(String args[]) throws IOException {

    RestHighLevelClient highLevelClient = EsConnection.loadClient();

    UpdateRequest request = new UpdateRequest(
        "twitter",
        "doc",
        "1");
    UpdateResponse updateResponse = highLevelClient.update(request);
    System.out.println("updateResponse    "+updateResponse);
    System.out.println("updateResponse  "+updateResponse.getId());
    EsConnection.closeClient(highLevelClient);
  }
}
