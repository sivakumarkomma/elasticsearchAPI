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
public class BoolQuery {
  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.boolQuery()
        .must(QueryBuilders.matchQuery("sa_indexID", "98"))
        .must(QueryBuilders.matchPhrasePrefixQuery("name", "act"))
        .must(QueryBuilders.matchQuery("sa_fileSize", "3210"))
    );
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());
    System.out.println("searchResponse  " + searchResponse);
    EsConnection.closeClient(highLevelClient);
  }
}
