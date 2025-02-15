package com.mysystem.controller;

import com.mysystem.model.HttpResult;
import com.mysystem.model.ro.VersionRecordRO;
import com.mysystem.model.vo.VersionRecordVO;
import com.mysystem.service.VersionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("版本更新记录")
@RestController
@RequestMapping("/api/versionRecord")
public class VersionRecordController {

    @Autowired
    private VersionRecordService versionRecordService;

    @ApiOperation(value = "新增记录")
    @PostMapping("/addRecord")
    public HttpResult<String> addRecord(@RequestBody VersionRecordRO ro){
        String result = versionRecordService.addRecord(ro);
        return HttpResult.success(result);
    }

    @ApiOperation(value = "更新记录")
    @PostMapping("/updateRecord")
    public HttpResult<String>  updateRecord(@RequestBody VersionRecordRO ro){
        String result = versionRecordService.updateRecord(ro);
        return HttpResult.success(result);
    }

    @ApiOperation(value = "查询记录")
    @GetMapping("/getRecord")
    public HttpResult<Map<String, List<VersionRecordVO>>> getRecord(@RequestParam(defaultValue = "1") Integer interval){
        Map<String, List<VersionRecordVO>> records = versionRecordService.getRecord(interval);
        return HttpResult.success(records);
    }
}
