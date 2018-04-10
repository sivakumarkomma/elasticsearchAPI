package com.elasticsearch;

import java.io.IOException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestHighLevelClient;

public class EsGetAPI {

  public static void main(String args[]) throws IOException {

    RestHighLevelClient highLevelClient = EsConnection.loadClient();

    GetRequest getRequest = new GetRequest(
        "twitter",
        "doc",
        "1");
    GetResponse getResponse = highLevelClient.get(getRequest);
    System.out.println("getResponse    "+getResponse);
    System.out.println("indexResponse  "+getResponse.getId());
    EsConnection.closeClient(highLevelClient);
  }
}
