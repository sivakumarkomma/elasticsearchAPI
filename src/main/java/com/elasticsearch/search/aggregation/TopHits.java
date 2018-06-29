package com.elasticsearch.search.aggregation;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import java.util.Arrays;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.tophits.ParsedTopHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class TopHits {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    //searchSourceBuilder.aggregation(AggregationBuilders.terms("sa_fileSize").size(3));
    String[] includes = new String[] {"sa_indexID","name"};

    searchSourceBuilder.aggregation(AggregationBuilders
        .topHits("sum_sa_fileSize")
        .sort("sa_fileSize", SortOrder.DESC)
        .fetchSource(includes,new String[]{})
    );
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("sum  " + searchResponse.getAggregations().get("sum_sa_fileSize").toString());
    System.out.println("sum  " + searchResponse.getAggregations().get("sum_sa_fileSize").getName());
    System.out.println("sum  " + searchResponse.getAggregations().get("sum_sa_fileSize").getType());
    ParsedTopHits parsedTopHits = searchResponse.getAggregations().get("sum_sa_fileSize");
    System.out.println(" parsedAvg.getValue()  " + parsedTopHits.getHits());
    System.out.println(" parsedAvg.getValue()  " + parsedTopHits.getHits().getHits());
    Arrays.stream(parsedTopHits.getHits().getHits()).forEach(System.out::println);
    EsConnection.closeClient(highLevelClient);
  }
}
