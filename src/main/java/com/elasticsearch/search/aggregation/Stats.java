package com.elasticsearch.search.aggregation;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.stats.ParsedStats;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ParsedExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 29/6/2018.
 */
public class Stats {
  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.aggregation(AggregationBuilders.stats("sta_sa_fileSize").field("sa_fileSize"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);

    System.out.println(" stats  " + searchResponse.getAggregations().get("sta_sa_fileSize").toString());
    System.out.println(" stats  " + searchResponse.getAggregations().get("sta_sa_fileSize").getName());
    System.out.println(" stats  " + searchResponse.getAggregations().get("sta_sa_fileSize").getType());
    ParsedStats parsedStats = searchResponse.getAggregations().get("sta_sa_fileSize");
    System.out.println(" parsedStats.getCount()  " + parsedStats.getCount());
    System.out.println(" parsedStats.getMin()  " + parsedStats.getMin());
    System.out.println(" parsedStats.getMax()  " + parsedStats.getMax());
    System.out.println(" parsedStats.getAvg()  " + parsedStats.getAvg());
    System.out.println(" parsedStats.getSum()  " + parsedStats.getSum());
    EsConnection.closeClient(highLevelClient);
  }
}
