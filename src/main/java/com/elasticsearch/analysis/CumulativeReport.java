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
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.metrics.avg.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class CumulativeReport {

  public static void main(String[] args) throws IOException {

    String startDate = Long.toString(new Date().getTime());

    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.set(Calendar.YEAR, cal.getWeekYear() - 7);
    String endDate = Long.toString(cal.getTimeInMillis());

    System.out.println(" startDate " + startDate);
    System.out.println(" endDate " + endDate);

    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    UsingQueryBuilder(startDate, endDate, highLevelClient);
    EsConnection.closeClient(highLevelClient);
  }

  private static void withQueryString(String startDate, String endDate, RestHighLevelClient highLevelClient) throws IOException {
    String initialQuery = "{\"_source\":{\"excludes\":[]},\"aggs\":{\"2\":{\"significant_terms\":{\"field\":\"VC_FILE_EXTENSION.keyword\",\"size\":10},\"aggs\":{\"3\":{\"date_histogram\":{\"field\":\"VC_MODIFIEDDATE\",\"interval\":\"1y\",\"time_zone\":\"Africa/Cairo\",\"min_doc_count\":1},\"aggs\":{\"1\":{\"sum\":{\"field\":\"VC_FILESIZE\"}}}}}}},\"stored_fields\":[\"*\"],\"script_fields\":{},\"docvalue_fields\":[\"VC_ACCESSEDON\",\"VC_ARCHIVEDATE\",\"VC_CREATIONDATE\",\"VC_MODIFIEDDATE\",\"VC_REPOACCESSEDON\"],\"query\":{\"bool\":{\"must\":[{\"match_all\":{}},{\"range\":{\"VC_MODIFIEDDATE\":{\"gte\":%s,\"lte\":%s,\"format\":\"epoch_millis\"}}}],\"filter\":[],\"should\":[],\"must_not\":[]}}}\r\n";
    String finalQuery = String.format(initialQuery, startDate, endDate);
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.queryStringQuery(finalQuery));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());
  }

  private static void UsingQueryBuilder(String startDate, String endDate, RestHighLevelClient highLevelClient) throws IOException {

    SearchResponse searchResponse = highLevelClient.search(new SearchRequest("twitter")
        .source(new SearchSourceBuilder()
            .aggregation(AggregationBuilders
                .dateHistogram("dateHistogram")
                .field("sa_date_creation")
                .dateHistogramInterval(DateHistogramInterval.YEAR)
                .minDocCount(1))
            .aggregation(AggregationBuilders
                .sum("sum")
                .field("sa_fileSize"))
            .query(QueryBuilders
                .boolQuery()
                .must(QueryBuilders.rangeQuery("sa_date_creation")
                .gt(endDate)
                .lt(startDate))
        )));
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());
    System.out.println("searchResponse  " + searchResponse);
    ParsedSum parsedSum = searchResponse.getAggregations().get("sum");
    System.out.println(" parsedAvg.getValue()  " + parsedSum.getValue());

  }
}
