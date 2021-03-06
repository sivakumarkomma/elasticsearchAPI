package com.elasticsearch.search.compound;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 27/6/2018.
 */
public class ConstantScoreQuery {
  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.constantScoreQuery(QueryBuilders.idsQuery("document").addIds("192.168.87.199-mina-paperkey.exe")));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());
    System.out.println("searchResponse  " + searchResponse);
    EsConnection.closeClient(highLevelClient);
  }
}
