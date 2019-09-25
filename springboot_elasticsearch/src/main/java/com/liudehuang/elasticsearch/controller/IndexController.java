package com.liudehuang.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.liudehuang.elasticsearch.entity.CreateIndexRequestParamParam;
import com.liudehuang.elasticsearch.entity.IndexRequestParam;
import com.liudehuang.elasticsearch.entity.UpdateIndexRequestParam;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-24
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-24
 * @UpdateRemark:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private RestHighLevelClient highLevelClient;

    /**
     * 创建索引
     * @return
     * @throws IOException
     */
    @PostMapping("/create")
    public String createIndex(@RequestBody CreateIndexRequestParamParam request) throws IOException {
        IndexRequest indexRequest = new IndexRequest(request.getIndex())
                .id(request.getId()).source(request.getMap());
        IndexResponse indexResponse = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        String index = indexResponse.getIndex();
        String id = indexResponse.getId();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            //处理（如果需要）首次创建文档的情况
            return "创建索引成功";
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            //处理（如果需要）已存在的文档被重写的情况
            return "更新索引成功";
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            //处理成功分片数量少于总分片数量的情况
            return null;
        }
        if (shardInfo.getFailed() > 0) {
            //处理潜在的故障
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                return reason;
            }
        }
        return null;
    }

    /**
     * 获取索引
     * @return
     * @throws IOException
     */
    @PostMapping("/get")
    public String getIndex(@RequestBody IndexRequestParam request) throws IOException {
        GetRequest getRequest = new GetRequest(request.getIndex(),request.getId());
        GetResponse response = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        return response.getSourceAsString();
    }

    /**
     * 判断索引是否存在
     * @return
     * @throws IOException
     */
    @PostMapping("/exist")
    public String existIndex(@RequestBody IndexRequestParam request) throws IOException {
        GetRequest getRequest = new GetRequest(request.getIndex(),request.getId());
        boolean exist = highLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        if(exist){
            return "已经存在";
        }else {
            return "不存在";
        }
    }


    @PostMapping("/delete")
    public String deleteIndex(@RequestBody IndexRequestParam request) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(request.getIndex(),request.getId());
        DeleteResponse response = highLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        String index = response.getIndex();
        String id = response.getId();
        long version = response.getVersion();
        ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            return "删除失败,处理成功分片数量少于总分片数量的情况";
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                return "删除失败,处理潜在故障"+reason;
            }
        }
        return "删除索引成功";
    }

    @PostMapping("/update")
    public String updateIndex(@RequestBody UpdateIndexRequestParam request) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(request.getIndex(), request.getId())
                .doc(request.getUpdateMap());
        UpdateResponse updateResponse = highLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        String index = updateResponse.getIndex();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            return "处理首次创建文档的情况";
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            return "处理文档更新的情况(更新成功)";
        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
            return "处理文件被删除的情况";
        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
            return "处理文档不受更新影响的情况，即未对文档执行任何操作（空转）";
        }
        return "更新成功";
    }

    /**
     * termvector会获取document中的某个field内的各个term的统计信息
     * @return
     * @throws IOException
     */
    @PostMapping("/termIndex")
    public String termIndex() throws IOException {
        TermVectorsRequest termVectorsRequest = new TermVectorsRequest("book","3");
        termVectorsRequest.setFields("name");
        TermVectorsResponse response =
                highLevelClient.termvectors(termVectorsRequest, RequestOptions.DEFAULT);
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        boolean found = response.getFound();
        System.out.println(index+type+id+found);
        return JSONObject.toJSONString(response);
    }


    @PostMapping("/bulkIndex")
    public String bulkIndex() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("book").id("4")
                .source(XContentType.JSON,"name", "水浒传","author","施耐庵","price",50.00,"description","四大名著之一"));
        request.add(new IndexRequest("book").id("5")
                .source(XContentType.JSON,"name", "红楼梦","author","曹雪芹","price",50.00,"description","四大名著之一"));
        BulkResponse bulkResponse = highLevelClient.bulk(request, RequestOptions.DEFAULT);
        for (BulkItemResponse bulkItemResponse : bulkResponse) {
            DocWriteResponse itemResponse = bulkItemResponse.getResponse();

            switch (bulkItemResponse.getOpType()) {
                case INDEX:
                case CREATE:
                    IndexResponse indexResponse = (IndexResponse) itemResponse;
                    break;
                case UPDATE:
                    UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                    break;
                case DELETE:
                    DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
            }
        }
        return "成功";
    }
}
