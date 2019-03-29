package com.bianxianmao.elasticsearch;


import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;


/**
 * @author sunzs
 * created on 2018/10/25
 */
public class EsTest {



    @Test
    // 直接在ElasticSearch中建立文档，自动创建索引

    public void demo1() throws IOException {
        //5.0之前创建方式
        // 创建连接搜索服务器对象
        /*Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress
                                .getByName("127.0.0.1"), 9300));*/

        //5.0之后的创建方式
        // 通过setting对象指定集群配置信息, 配置的集群名(配置信息)
        Settings settings = Settings.builder().put("cluster.name", "my-application") // 设置ES实例的名称
                .put("client.transport.sniff", true) // 自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .build();
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1",9300)));
        // 描述json 数据
        /*
         * {id:xxx, title:xxx, content:xxx}
         */
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("id", 1)
                .field("title", "ElasticSearch是一个基于Lucene的搜索服务器")
                .field("content",
                        "它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。")
                .field("测试一下","测试完")
                .endObject();
        // 建立文档对象
        client.prepareIndex("test", "article", "1").setSource(builder).get();

        // 关闭连接
        client.close();
    }


    public static TransportClient getClient(){
        Settings settings = Settings.builder().put("cluster.name", "my-application") // 设置ES实例的名称
                .put("client.transport.sniff", true) // 自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .build();
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1",9300)));
        return client;
    }



    @Test
    public  void createIndex(){
        String index = "test2";
     CreateIndexResponse indexResponse = getClient().admin().indices().prepareCreate(index).get();
        System.out.println(indexResponse.isAcknowledged());


    }


    @Test
    public void addMapping(){
        try {
            XContentBuilder xContentBuilder =  XContentFactory.jsonBuilder().startObject()
                    .field("properties").startObject()
                        .field("name").startObject()
                            .field("index","not_analyzed")
                            .field("type","string")
                            .endObject()
                        .field("age").startObject()
                            .field("index","not_analyzd")
                            .field("type","integer")
                        .endObject()
                    .endObject()
                .endObject();

            System.out.println(xContentBuilder.toString());
         PutMappingRequest mappingRequest = Requests.putMappingRequest("test3create").source(xContentBuilder).type("article");
            getClient().admin().indices().putMapping(mappingRequest).actionGet();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void deleteIndex(){

    }








}
