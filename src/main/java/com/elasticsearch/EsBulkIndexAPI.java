package com.elasticsearch;

import java.io.IOException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class EsBulkIndexAPI {

  public static void main(String args[]) throws IOException {

    RestHighLevelClient highLevelClient = EsConnection.loadClient();

    BulkRequest request = new BulkRequest();
    request.add(new IndexRequest("twitter", "doc", "1")
        .source(XContentType.JSON,"field", "foo"));
    request.add(new IndexRequest("twitter", "doc", "2")
        .source(XContentType.JSON,"field", "bar"));
    request.add(new IndexRequest("twitter", "doc", "3")
        .source(XContentType.JSON,"field", "baz"));

    BulkResponse bulkResponse = highLevelClient.bulk(request);
    System.out.println("bulkResponse  "+bulkResponse);
    EsConnection.closeClient(highLevelClient);
  }
}
