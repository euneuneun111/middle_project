package com.application.service;

import java.util.List;
import com.application.dto.FundingOptionVO;

public interface FundingOptionService {

    List<FundingOptionVO> getOptionsByFundingId(int fundingId);

    int insertFundingOption(FundingOptionVO vo);

    int deleteOptionsByFundingId(int fundingId);
}
