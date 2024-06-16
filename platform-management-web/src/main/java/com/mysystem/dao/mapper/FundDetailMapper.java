package com.mysystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysystem.dao.po.fund.FundDetailPO;
import com.mysystem.model.vo.FundDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FundDetailMapper extends BaseMapper<FundDetailPO> {


    @Select("<script>" +
            " select t1.*,t2.user_name as relativeUserName from t_fund_detail t1 left join t_user_info t2 on t1.relative_user_id = t2.id " +
            " where t1.type = #{type} and t1.deleted = 0 order by t1.id desc" +
            "</script>")
    Page<FundDetailVO> selectFundDetailPage(Page<FundDetailPO> pageHelper, Integer type);
}
