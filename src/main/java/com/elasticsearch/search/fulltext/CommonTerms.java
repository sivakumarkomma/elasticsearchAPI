package com.elasticsearch.search.fulltext;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class CommonTerms {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.commonTermsQuery("field", "foo"));
    //searchSourceBuilder.query(QueryBuilders.commonTermsQuery("field", "the foo").lowFreqOperator(Operator.AND));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  "+searchResponse);
    EsConnection.closeClient(highLevelClient);
  }
}
