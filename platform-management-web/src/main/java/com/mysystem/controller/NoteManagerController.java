package com.mysystem.controller;

import com.mysystem.annotation.LogRecord;
import com.mysystem.model.HttpResult;
import com.mysystem.model.PageTemplate;
import com.mysystem.model.ro.NoteQueryRO;
import com.mysystem.model.ro.NoteRO;
import com.mysystem.model.vo.NoteVO;
import com.mysystem.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("笔记管理")
@RequestMapping("/api/note")
public class NoteManagerController {

    @Autowired
    private NoteService noteService;

    @LogRecord
    @ApiOperation("保存笔记")
    @PostMapping("/save")
    public HttpResult<String> saveNote(@RequestBody NoteRO ro) {
        noteService.saveNote(ro);
        return HttpResult.success();
    }

    @ApiOperation("列表")
    @PostMapping("/page")
    public HttpResult<PageTemplate<NoteVO>> page(@RequestBody NoteQueryRO queryRO) {
        return HttpResult.success(noteService.notePage(queryRO));
    }

    @ApiOperation("详情")
    @GetMapping("/detail")
    public HttpResult<NoteVO> detail(@RequestParam Integer id) {
        return HttpResult.success(noteService.noteDetail(id));
    }

    @LogRecord
    @ApiOperation("删除笔记")
    @GetMapping("/delete")
    public HttpResult<String> delete(@RequestParam Integer id) {
        return HttpResult.success(noteService.noteDelete(id));
    }
}
