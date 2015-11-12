package com.ambimmort.nisp3.rest;


import com.ambimmort.nisp3.service.SolrService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hedingwei on 8/10/15.
 */

@Api(value = "/solrservice", description = "Solr服务")
@Controller("/api/v1/solrservice")
@RequestMapping("/api/v1/solrservice")
public class SolrRest {


    @Autowired
    private SolrService solrService;

    @ApiOperation(value = "保存文档", notes = "保存文档", response = JSONObject.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = JSONObject.class),
            @ApiResponse(code = 500, message = "Internal server error")})

    @RequestMapping(value = "/solr/save",method = RequestMethod.POST)
    @ResponseBody
    public void save(@RequestParam("document")String document){

        this.solrService.save(document);
    }



}
