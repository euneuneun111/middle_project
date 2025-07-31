package com.application.dao;

import java.util.List;
import com.application.dto.FundingOptionVO;

public interface FundingOptionDAO {

    List<FundingOptionVO> getOptionsByFundingId(int fundingId);

    int insertFundingOption(FundingOptionVO vo);

    int deleteOptionsByFundingId(int fundingId);
}