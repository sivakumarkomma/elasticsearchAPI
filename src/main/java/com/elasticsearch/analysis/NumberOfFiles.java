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
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 30/6/2018.
 */
public class NumberOfFiles {

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

  private static void UsingQueryBuilder(String startDate, String endDate, RestHighLevelClient highLevelClient) throws IOException {

    SearchResponse searchResponse = highLevelClient.search(new SearchRequest("twitter")
        .source(new SearchSourceBuilder()
            .query(QueryBuilders
                .boolQuery()
                .must(QueryBuilders.rangeQuery("sa_date_creation")
                    .gt(endDate)
                    .lt(startDate))
            )));
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());

  }
}
