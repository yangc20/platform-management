package com.mysystem.task;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.gson.reflect.TypeToken;
import com.mysystem.dao.mapper.LotteryTicketMapper;
import com.mysystem.dao.po.LotteryTicketPO;
import com.mysystem.model.response.LotteryTicketDetailData;
import com.mysystem.model.response.TicketResponseData;
import com.mysystem.service.LotteryTicketService;
import com.mysystem.service.SystemService;
import com.mysystem.utils.ApacheHttpUtils;
import com.mysystem.utils.OkHttpUtils;
import com.mysystem.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class LotteryTicketSyncTask {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LotteryTicketService lotteryTicketService;

    @Autowired
    private LotteryTicketMapper lotteryTicketMapper;

    private Long expireTime = 3 * 60 * 1000L;

    @Scheduled(cron = "36 13 13-18 * * ?")
    public void run() {

        String key = SystemService.getEnv() + "_LotteryTicketSyncTask";
        if (!RedisUtils.tryLock(key, expireTime)) {
            log.info("当前定时任务执行过于频繁，请稍后再试");
            return;
        }

        log.info("开始同步开奖信息");
        sync();
        log.info("同步开奖信息结束,时间：{}", new Date());
    }

    public void sync() {
        List<LotteryTicketDetailData> httpData = getHttpData();
        List<LotteryTicketPO> poList = new ArrayList<>();
        if (CollectionUtils.isEmpty(httpData)) {
            log.info("暂未同步到开奖信息");
            return;
        }
        httpData.forEach(data -> {
            String dateAndWeek = data.getDate();
            String[] split = dateAndWeek.split("\\(");
            LotteryTicketPO po = new LotteryTicketPO();
            po.setName(data.getName());
            po.setBlue(data.getBlue());
            po.setRed(data.getRed());
            po.setCode(data.getCode());
            po.setContent(data.getContent());
            po.setDate(split[0]);
            po.setWeek(split[1].substring(0, 1));
            po.setPoolmoney(data.getPoolmoney());
            po.setCreateTime(new Date());
            po.setUpdateTime(new Date());
            poList.add(po);
        });
        lotteryTicketMapper.insertOrUpdate(poList);
        log.info("本次查询开奖信息数量:{}", poList.size());
    }

    public List<LotteryTicketDetailData> getHttpData() {
        TypeToken<TicketResponseData<LotteryTicketDetailData>> typeToken = new TypeToken<TicketResponseData<LotteryTicketDetailData>>() {
        };
        String url = "https://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&pageNo=1&pageSize=30&systemType=PC";
        Map<String, String> headerMap = new HashMap<>();
        TicketResponseData<LotteryTicketDetailData> responseData = new TicketResponseData<>();
        headerMap.put("Cookie", "HMF_CI=09deeca4cb0aa0363c21b9c42aa03628d7469326556153c2e4c4607a90f9aa35d458917a8a2d202b608bf0de5501218f1d552d28ba0a5e7e62c714ff1af1660bb8; 21_vq=7");
        try {
            responseData = (TicketResponseData<LotteryTicketDetailData>) ApacheHttpUtils.doGet(url, headerMap, typeToken);
        } catch (Exception e) {
            log.error("同步开奖信息错误，等待下次重试", e);
        }

        if (Objects.nonNull(responseData)) {
            return responseData.getResult();
        } else {
            return new ArrayList<>();
        }

    }
}
