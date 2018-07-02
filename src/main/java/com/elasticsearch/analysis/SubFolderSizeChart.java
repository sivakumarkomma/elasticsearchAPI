package com.elasticsearch.analysis;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class SubFolderSizeChart {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("vc_rootParentPath", "C:\\Users\\siva\\git"));
    searchSourceBuilder.aggregation(AggregationBuilders.terms("group_by").field("parent_path_md5.keyword")
        .subAggregation(AggregationBuilders.sum("sum").field("sa_fileSize"))
    );
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());
    ParsedStringTerms parsedStringTerms = searchResponse.getAggregations().get("group_by");
    parsedStringTerms.getBuckets()
        .forEach(o -> {
          System.out.println("((Histogram.Bucket) o).getDocCount()  " + ((ParsedStringTerms.ParsedBucket) o).getDocCount());
          System.out.println("((Histogram.Bucket) o).getKeyAsString()  " + ((ParsedStringTerms.ParsedBucket) o).getKeyAsString());
          System.out.println("((Histogram.Bucket) o).getAggregations()  " + ((ParsedStringTerms.ParsedBucket) o).getAggregations());
          ParsedSum parsedSumm = ((ParsedStringTerms.ParsedBucket) o).getAggregations().get("sum");
          System.out.println(" parsedAvg.getValue()  " + parsedSumm.getValue());
        });
    EsConnection.closeClient(highLevelClient);
  }
}
