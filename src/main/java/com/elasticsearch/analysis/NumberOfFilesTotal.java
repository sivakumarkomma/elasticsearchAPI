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
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 30/6/2018.
 */
public class NumberOfFilesTotal {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    UsingQueryBuilder(highLevelClient);
    EsConnection.closeClient(highLevelClient);
  }

  private static void UsingQueryBuilder(RestHighLevelClient highLevelClient) throws IOException {

    SearchResponse searchResponse = highLevelClient.search(new SearchRequest("twitter")
        .source(new SearchSourceBuilder()
            .aggregation(AggregationBuilders
                .count("total_count")
                .field("sa_indexID")
            )));
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());

  }
}
