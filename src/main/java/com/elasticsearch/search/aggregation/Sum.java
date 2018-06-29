package com.elasticsearch.search.aggregation;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.avg.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 29/6/2018.
 */
public class Sum {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.aggregation(AggregationBuilders.sum("sum_sa_fileSize").field("sa_fileSize"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);

    System.out.println("sum  " + searchResponse.getAggregations().get("sum_sa_fileSize").toString());
    System.out.println("sum  " + searchResponse.getAggregations().get("sum_sa_fileSize").getName());
    System.out.println("sum  " + searchResponse.getAggregations().get("sum_sa_fileSize").getType());
    ParsedSum parsedSum = searchResponse.getAggregations().get("sum_sa_fileSize");
    System.out.println(" parsedAvg.getValue()  " + parsedSum.getValue());
    EsConnection.closeClient(highLevelClient);
  }
}
