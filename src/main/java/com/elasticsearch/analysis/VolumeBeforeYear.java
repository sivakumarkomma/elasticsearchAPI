package com.elasticsearch.analysis;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 1/7/2018.
 */
public class VolumeBeforeYear {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.rangeQuery("sa_fileSize").lte("5000"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());
    dateRange(highLevelClient, "sa_last_access",1 );
    dateRange(highLevelClient, "last_modified",2);
    EsConnection.closeClient(highLevelClient);
  }

  private static void dateRange(RestHighLevelClient highLevelClient, String fieldName,  int year) throws IOException {
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.set(Calendar.YEAR, cal.getWeekYear() - year);
    String date = Long.toString(cal.getTimeInMillis());

    System.out.println(" date " + date);
    searchSourceBuilder.query(QueryBuilders.rangeQuery(fieldName).lte(date));
    searchSourceBuilder.aggregation(AggregationBuilders.sum("sum").field("sa_fileSize"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());
    ParsedSum parsedSum = searchResponse.getAggregations().get("sum");
    System.out.println(" parsedAvg.getValue()  " + parsedSum.getValue());
  }
}
