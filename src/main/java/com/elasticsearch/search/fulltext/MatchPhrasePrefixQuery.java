package com.elasticsearch.search.fulltext;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class MatchPhrasePrefixQuery {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("field", "b"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  "+searchResponse);
    EsConnection.closeClient(highLevelClient);
  }
}