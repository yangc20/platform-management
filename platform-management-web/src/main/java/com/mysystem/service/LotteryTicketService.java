package com.mysystem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import com.mysystem.common.config.SelfLotteryTicketConstant;
import com.mysystem.dao.mapper.LotteryTicketMapper;
import com.mysystem.dao.po.LotteryTicketPO;
import com.mysystem.model.vo.LotteryTicketNumberVO;
import com.mysystem.model.vo.LotteryTicketVO;
import com.mysystem.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotteryTicketService extends ServiceImpl<LotteryTicketMapper, LotteryTicketPO> {

    @Autowired
    private LotteryTicketMapper lotteryTicketMapper;

    @Value("${redNums}")
    private String redNumStr;

    @Value("${blueNums}")
    private String blueNumStr;

    public LotteryTicketVO home() {
        LotteryTicketVO lotteryTicketVO = new LotteryTicketVO();
        LotteryTicketPO lotteryTicketPO = lotteryTicketMapper.selectLastTicket();
        // 获取所选号码列表
//        List<String> redNums = SelfLotteryTicketConstant.redNums;
//        List<String> blueNums = SelfLotteryTicketConstant.blueNums;
        List<String> redNums = Arrays.stream(redNumStr.split(" ")).collect(Collectors.toList());
        List<String> blueNums = Arrays.stream(blueNumStr.split(" ")).collect(Collectors.toList());

        List<LotteryTicketNumberVO> selfNumberVos = new ArrayList<>();
        redNums.forEach(rStr -> {
            int i = redNums.indexOf(rStr);
            String bStr = blueNums.get(i);
            LotteryTicketNumberVO numberVO = analysisStringNum(rStr, bStr, lotteryTicketPO.getRed(), lotteryTicketPO.getBlue());
            selfNumberVos.add(numberVO);
        });
        lotteryTicketVO.setSelfNumberVOs(selfNumberVos);

        // 获取开奖号码
        lotteryTicketVO.setWinNumberVO(analysisStringNum(lotteryTicketPO.getRed(), lotteryTicketPO.getBlue(), null, null));
        lotteryTicketVO.setContent(lotteryTicketPO.getContent());
        lotteryTicketVO.setLastWinTime(DateUtils.strToDate(lotteryTicketPO.getDate()));

        return lotteryTicketVO;
    }

    public LotteryTicketNumberVO analysisStringNum(String redNumStr, String blueNumStr, String winRedNumStr, String winBlueNumStr) {
        LotteryTicketNumberVO numberVO = new LotteryTicketNumberVO();
        List<String> redNums = Arrays.stream(redNumStr.split(",")).collect(Collectors.toList());
        List<String> blueNums = Arrays.stream(blueNumStr.split(",")).collect(Collectors.toList());
        numberVO.setRedNums(redNums);
        numberVO.setBlueNums(blueNums);

        // 判断是否需要解析中奖的号码
        if (!StringUtils.isEmpty(winRedNumStr) && !StringUtils.isEmpty(winBlueNumStr)) {
            List<String> winRedNums = Arrays.stream(winRedNumStr.split(",")).collect(Collectors.toList());
            List<String> winBlueNums = Arrays.stream(winBlueNumStr.split(",")).collect(Collectors.toList());
            List<String> scoreRedNums = redNums.stream().filter(id -> winRedNums.contains(id)).collect(Collectors.toList());
            List<String> scoreBlueNums = blueNums.stream().filter(id -> winBlueNums.contains(id)).collect(Collectors.toList());
            numberVO.setScoreRedNums(scoreRedNums);
            numberVO.setScoreBlueNums(scoreBlueNums);
        }
        return numberVO;
    }
}
