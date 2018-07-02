package com.elasticsearch.search.aggregation;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.cardinality.ParsedCardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 29/6/2018.
 */
public class Cardinality {
  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.aggregation(AggregationBuilders.cardinality("uni_sa_fileSize").field("vc_rootParentPath.keyword"));
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " +searchResponse);
    System.out.println("agg  " + searchResponse.getAggregations().get("uni_sa_fileSize").toString());
    System.out.println("agg  " + searchResponse.getAggregations().get("uni_sa_fileSize").getName());
    System.out.println("agg  " + searchResponse.getAggregations().get("uni_sa_fileSize").getType());
    ParsedCardinality parsedCardinality = searchResponse.getAggregations().get("uni_sa_fileSize");
    System.out.println(" parsedCardinality.getValue()  " + parsedCardinality.getValue());
    EsConnection.closeClient(highLevelClient);
  }
}
