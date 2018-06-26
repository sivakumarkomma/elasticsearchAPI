package com.elasticsearch.crud;

import com.elasticsearch.EsConnection;
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
    request.add(new IndexRequest("twitter", "doc", "4")
        .source(XContentType.JSON,"field", "This is sample"));
    request.add(new IndexRequest("twitter", "doc", "5")
        .source(XContentType.JSON,"field", "This is sample indexing for search "));
    request.add(new IndexRequest("twitter", "doc", "6")
        .source(XContentType.JSON,"field", "This is sample indexing for search with rest high level client"));

    BulkResponse bulkResponse = highLevelClient.bulk(request);
    System.out.println("bulkResponse  "+bulkResponse);
    EsConnection.closeClient(highLevelClient);
  }
}
