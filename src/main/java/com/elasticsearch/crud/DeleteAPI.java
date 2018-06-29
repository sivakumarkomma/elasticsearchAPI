package com.elasticsearch.crud;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RestHighLevelClient;

public class DeleteAPI {

  public static void main(String args[]) throws IOException {

    RestHighLevelClient highLevelClient = EsConnection.loadClient();

    DeleteRequest request = new DeleteRequest(
        "twitter",
        "doc",
        "1");
    DeleteResponse deleteResponse = highLevelClient.delete(request);
    System.out.println("deleteResponse    " + deleteResponse);
    System.out.println("deleteResponse  " + deleteResponse.getId());
    EsConnection.closeClient(highLevelClient);
  }
}
