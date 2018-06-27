package com.elasticsearch.search.termlevel;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 26/6/2018.
 */
public class ExistsQuery {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("datarepositorysi");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.existsQuery("sa_MetaData"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  "+searchResponse.getHits().getTotalHits());
    matchNot(highLevelClient);
    EsConnection.closeClient(highLevelClient);
  }

  private static void matchNot(RestHighLevelClient highLevelClient) throws IOException {
    SearchRequest searchRequest = new SearchRequest("datarepositorysi");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    QueryBuilder query = QueryBuilders.boolQuery()
        .mustNot(QueryBuilders.existsQuery("sa_MetaData"));
    searchSourceBuilder.query(query);
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  "+searchResponse.getHits().getTotalHits());
    EsConnection.closeClient(highLevelClient);
  }
}
