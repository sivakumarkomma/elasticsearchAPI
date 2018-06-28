package com.elasticsearch.search.termlevel;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class RangeQuery {

  public static void main(String[] args) throws IOException{
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.rangeQuery("sa_fileSize").lte("5000"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  "+searchResponse.getHits().getTotalHits());
    dateRange(highLevelClient);
    EsConnection.closeClient(highLevelClient);
  }

  private static void dateRange(RestHighLevelClient highLevelClient) throws IOException{
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.rangeQuery("sa_date_creation").gt("2015-09-08T18:28:26Z").lt("2015-10-08T18:28:26Z"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  "+searchResponse.getHits().getTotalHits());
  }

}
