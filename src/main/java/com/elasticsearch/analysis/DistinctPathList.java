package com.elasticsearch.analysis;

import com.elasticsearch.EsConnection;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by sivakumar on 1/7/2018.
 */
public class DistinctPathList {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    SearchRequest searchRequest = new SearchRequest("twitter");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("vc_rootParentPath", "C:\\Users\\siva\\git"));
    searchRequest.source(searchSourceBuilder);
    String[] includeFields = new String[] {"vc_immediateParentPath", "parent_path_md5"};
    String[] excludeFields = new String[] {"sa*"};
    searchSourceBuilder.fetchSource(includeFields, excludeFields);
    SearchResponse searchResponse = highLevelClient.search(searchRequest);
    System.out.println("searchResponse  " + searchResponse.getHits().getTotalHits());
    System.out.println("searchResponse  " + searchResponse);
    SearchHits hits = searchResponse.getHits();
    SearchHit[] searchHits = hits.getHits();
    Map<String, String> map = new HashMap<String,String>();
    Arrays.stream(searchHits).forEach(documentFields -> {
      System.out.println(documentFields.getSourceAsMap());
      Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
      String vc_immediateParentPath = (String) sourceAsMap.get("vc_immediateParentPath");
      String parent_path_md5 = (String) sourceAsMap.get("parent_path_md5");
      System.out.println(vc_immediateParentPath+ ":" + parent_path_md5);
      map.put(parent_path_md5, vc_immediateParentPath);
    });
    System.out.println(map);
    EsConnection.closeClient(highLevelClient);
  }
}
