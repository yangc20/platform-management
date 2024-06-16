package com.mysystem.controller;

import com.mysystem.annotation.LogRecord;
import com.mysystem.model.PageTemplate;
import com.mysystem.model.ro.FundDetailRO;
import com.mysystem.model.vo.FundDetailVO;
import com.mysystem.model.vo.FundInfoVO;
import com.mysystem.model.vo.FundRelativeUserVO;
import com.mysystem.service.FundDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("资金管理")
@RestController
@RequestMapping("/api/fund")
public class FundManagerController {

    @Autowired
    private FundDetailService fundDetailService;

    @LogRecord
    @ApiOperation("添加记录")
    @PostMapping("/save")
    public boolean saveFundDetail(@RequestBody FundDetailRO ro) {
        return fundDetailService.saveFundDetail(ro);
    }

    @LogRecord
    @ApiOperation("修改目标")
    @PostMapping("/update/target")
    public boolean updateTarget(@RequestBody FundDetailRO ro) {
        return fundDetailService.updateTarget(ro);
    }

    @LogRecord
    @ApiOperation("查询记录详情")
    @GetMapping("/detail")
    public PageTemplate<FundDetailVO> queryDetail(@RequestParam Integer type,
                                                  @RequestParam Integer pageNum,
                                                  @RequestParam Integer pageSize) {
        return fundDetailService.queryDetail(type, pageNum, pageSize);
    }


    @ApiOperation("获取首页内容")
    @GetMapping("/homePage")
    public FundInfoVO getHomePage() {
        return fundDetailService.homeIndex();
    }

    @LogRecord
    @ApiOperation("删除记录")
    @GetMapping("/delete")
    public String deleteDetail(@RequestParam Integer id) {
        return fundDetailService.delete(id);
    }

    @ApiOperation("获取已有的相关人")
    @GetMapping("/existed/relativeUser")
    public List<FundRelativeUserVO> getExistedRelativeUser(@RequestParam Integer type) {
        return fundDetailService.getRelativeUser(type);
    }
}
