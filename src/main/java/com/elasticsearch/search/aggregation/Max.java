package com.elasticsearch.search.aggregation;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.ParsedMax;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 29/6/2018.
 */
public class Max {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.aggregation(AggregationBuilders.max("max_sa_fileSize").field("sa_fileSize"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);

    System.out.println(" max  " + searchResponse.getAggregations().get("max_sa_fileSize").toString());
    System.out.println(" max  " + searchResponse.getAggregations().get("max_sa_fileSize").getName());
    System.out.println(" max  " + searchResponse.getAggregations().get("max_sa_fileSize").getType());
    ParsedMax parsedMax = searchResponse.getAggregations().get("max_sa_fileSize");
    System.out.println(" parsedMax.getValue()  " + parsedMax.getValue());
    EsConnection.closeClient(highLevelClient);
  }
}
