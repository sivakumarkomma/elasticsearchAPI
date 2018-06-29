package com.elasticsearch.search.aggregation;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.valuecount.ParsedValueCount;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 29/6/2018.
 */
public class ValueCount {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.aggregation(AggregationBuilders.count("count").field("sa_fileSize"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);

    System.out.println("count  " + searchResponse.getAggregations().get("count").toString());
    System.out.println("count  " + searchResponse.getAggregations().get("count").getName());
    System.out.println("count  " + searchResponse.getAggregations().get("count").getType());
    ParsedValueCount parsedValueCount = searchResponse.getAggregations().get("count");
    System.out.println(" parsedValueCount.getValue()  " + parsedValueCount.getValue());
    EsConnection.closeClient(highLevelClient);
  }
}
