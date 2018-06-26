package com.elasticsearch.search.fulltext;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class SimpleQueryString {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    String queryString = "field: foo| field: bar | field: baz";

    searchSourceBuilder.query(QueryBuilders.simpleQueryStringQuery(queryString));
    searchRequest.source(searchSourceBuilder);
    System.out.println("searchRequest  "+searchRequest);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  "+searchResponse);
    EsConnection.closeClient(highLevelClient);
  }
}
