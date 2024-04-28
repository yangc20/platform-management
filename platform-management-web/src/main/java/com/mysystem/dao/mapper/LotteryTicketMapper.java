package com.mysystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysystem.dao.po.LotteryTicketPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LotteryTicketMapper extends BaseMapper<LotteryTicketPO> {


    @Update("<script>" +
            " insert into t_lottery_ticket (name,code,blue,red,content,date,week,poolmoney,create_time,update_time) " +
            " values" +
            " <foreach collection='poList' item='item' index='index' separator=','>" +
            " (#{item.name},#{item.code},#{item.blue},#{item.red},#{item.content},#{item.date}," +
            " #{item.week},#{item.poolmoney},#{item.createTime},#{item.updateTime})" +
            "</foreach>" +
            " on duplicate key update blue = values(blue),red = values(red),content = values(content),date = values(date)," +
            " week = values(week),poolmoney = values(poolmoney),update_time = values(update_time) " +
            "</script>")
    void insertOrUpdate(@Param("poList") List<LotteryTicketPO> poList);

    @Select("select * from  t_lottery_ticket order by date desc limit 1")
    LotteryTicketPO selectLastTicket();
}
