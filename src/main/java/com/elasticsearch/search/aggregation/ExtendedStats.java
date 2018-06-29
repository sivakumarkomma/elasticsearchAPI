package com.elasticsearch.search.aggregation;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ParsedExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 29/6/2018.
 */
public class ExtendedStats {
  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.aggregation(AggregationBuilders.extendedStats("ext_sa_fileSize").field("sa_fileSize"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);

    System.out.println(" extendedStats  " + searchResponse.getAggregations().get("ext_sa_fileSize").toString());
    System.out.println(" extendedStats  " + searchResponse.getAggregations().get("ext_sa_fileSize").getName());
    System.out.println(" extendedStats  " + searchResponse.getAggregations().get("ext_sa_fileSize").getType());
    ParsedExtendedStats parsedExtendedStats = searchResponse.getAggregations().get("ext_sa_fileSize");
    System.out.println(" parsedExtendedStats.getCount()  " + parsedExtendedStats.getCount());
    System.out.println(" parsedExtendedStats.getMin()  " + parsedExtendedStats.getMin());
    System.out.println(" parsedExtendedStats.getMax()  " + parsedExtendedStats.getMax());
    System.out.println(" parsedExtendedStats.getAvg()  " + parsedExtendedStats.getAvg());
    System.out.println(" parsedExtendedStats.getSum()  " + parsedExtendedStats.getSum());
    EsConnection.closeClient(highLevelClient);
  }
}
