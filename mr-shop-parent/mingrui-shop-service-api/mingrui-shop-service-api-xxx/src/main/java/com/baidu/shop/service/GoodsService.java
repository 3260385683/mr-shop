package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SkuDTO;
import com.baidu.shop.dto.SpuDTO;
import com.baidu.shop.entity.SpuDetailEntity;
import com.baidu.shop.entity.SpuEntity;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "商品接口")
public interface GoodsService {

    @ApiOperation(value = "获取spu信息")
    @GetMapping(value = "goods/getSpuInfo")
    Result<PageInfo<SpuEntity>> getSpuInfo(SpuDTO spuDTO);

    @ApiOperation(value = "保存商品信息")
    @PostMapping(value = "goods/save")
    Result<JsonObject> addInfo(@RequestBody SpuDTO spuDTO);

    @ApiOperation(value = "获取spu详情信息")
    @GetMapping(value = "goods/getSpuDetailBydSpu")
    Result<SpuDetailEntity> getSpuDetailBydSpu(Integer spuId);

    @ApiOperation(value = "获取sku信息")
    @GetMapping(value = "goods/getSkuBySpuId")
    Result<SkuDTO> getSkuBySpuId(Integer spuId);

    @ApiOperation(value = "修改商品")
    @PutMapping(value = "goods/save")
    Result<JSONObject> editGoods(@RequestBody SpuDTO spuDTO);

    @ApiOperation(value = "删除商品")
    @DeleteMapping(value = "goods/del")
    Result<JSONObject> delGoods(Integer spuId);
}
