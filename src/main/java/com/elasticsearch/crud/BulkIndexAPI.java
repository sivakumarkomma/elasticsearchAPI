package com.elasticsearch.crud;

import static com.elasticsearch.util.ApplicationConstants.FILE_NAME;
import static com.elasticsearch.util.ApplicationConstants.INDEX_NAME;
import static com.elasticsearch.util.ApplicationConstants.TYPE;
import static com.elasticsearch.util.Util.preparePathBasedID;

import com.elasticsearch.EsConnection;
import com.elasticsearch.dto.DocumentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class BulkIndexAPI {

  public static void main(String args[]) throws IOException {

    RestHighLevelClient highLevelClient = EsConnection.loadClient();
    InputStream inputStream = BulkIndexAPI.class.getResourceAsStream(FILE_NAME);
    ObjectMapper objectMapper = new ObjectMapper();
    DocumentDto[] array = objectMapper.readValue(inputStream, DocumentDto[].class);
    BulkRequest request = new BulkRequest();
    Arrays.asList(array).forEach(documentDto ->
    {
      try {
        String jsonString = objectMapper.writeValueAsString(documentDto);
        System.out.println("jsonString" + jsonString);
        request.add(new IndexRequest(INDEX_NAME, TYPE, preparePathBasedID(documentDto.getId()))
            .source(jsonString, XContentType.JSON));
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }

    });
    System.out.println("request  " + request);
    BulkResponse bulkResponse = highLevelClient.bulk(request);
    System.out.println("bulkResponse  " + bulkResponse);
    EsConnection.closeClient(highLevelClient);
  }
}
