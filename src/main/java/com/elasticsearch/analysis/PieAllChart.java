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
import org.elasticsearch.search.aggregations.bucket.significant.ParsedSignificantStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class PieAllChart {

  public static void main(String[] args) throws IOException {

    String startDate = Long.toString(new Date().getTime());

    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.set(Calendar.YEAR, cal.getWeekYear() - 7);
    String endDate = Long.toString(cal.getTimeInMillis());

    System.out.println(" startDate " + startDate);
    System.out.println(" endDate " + endDate);
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.rangeQuery("sa_date_creation").gt(endDate).lt(startDate));
    searchSourceBuilder.aggregation(AggregationBuilders.terms("group_by").field("vc_file_extension.keyword")
        .subAggregation(AggregationBuilders.sum("sum").field("sa_fileSize"))
    );
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());
    ParsedStringTerms parsedStringTerms = searchResponse.getAggregations().get("group_by");
    parsedStringTerms.getBuckets()
        .forEach(o -> {
          System.out.println("((ParsedStringTerms.Bucket) o).getDocCount()  " + ((ParsedStringTerms.ParsedBucket) o).getDocCount());
          System.out.println("((ParsedStringTerms.Bucket) o).getKeyAsString()  " + ((ParsedStringTerms.ParsedBucket) o).getKeyAsString());
          System.out.println("((ParsedStringTerms.Bucket) o).getAggregations()  " + ((ParsedStringTerms.ParsedBucket) o).getAggregations());
          ParsedSum parsedSumm = ((ParsedStringTerms.ParsedBucket) o).getAggregations().get("sum");
          System.out.println(" parsedAvg.getValue()  " + parsedSumm.getValue());
        });
    EsConnection.closeClient(highLevelClient);
  }
}
