package com.elasticsearch.search.bucketaggs;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import java.util.stream.Collectors;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 29/6/2018.
 */
public class DateHistogramExample {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.aggregation(AggregationBuilders.dateHistogram("count").field("sa_date_creation")
        .dateHistogramInterval(DateHistogramInterval.YEAR));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " + searchResponse);
    System.out.println("dateHistogram  " + searchResponse.getAggregations().get("count").toString());
    System.out.println("dateHistogram  " + searchResponse.getAggregations().get("count").getName());
    System.out.println("dateHistogram  " + searchResponse.getAggregations().get("count").getType());
    ParsedDateHistogram parsedDateHistogram = searchResponse.getAggregations().get("count");
        parsedDateHistogram.getBuckets().stream()
        .forEach(o -> {
          System.out.println("((Histogram.Bucket) o).getDocCount()  " + ((Histogram.Bucket) o).getDocCount());
          System.out.println("((Histogram.Bucket) o).getKeyAsString()  " + ((Histogram.Bucket) o).getKeyAsString());
          System.out.println("((Histogram.Bucket) o).getAggregations()  " + ((Histogram.Bucket) o).getAggregations());
        });
    EsConnection.closeClient(highLevelClient);
  }
}
