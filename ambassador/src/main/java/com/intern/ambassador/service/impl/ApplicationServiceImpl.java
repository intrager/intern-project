package com.intern.ambassador.service.impl;

import com.intern.ambassador.data.dto.ApplicationDto;
import com.intern.ambassador.data.dto.ApplicationResponseDto;
import com.intern.ambassador.data.dto.ChangeContentsDto;
import com.intern.ambassador.data.entity.Application;
import com.intern.ambassador.data.repository.ApplicationRepository;
import com.intern.ambassador.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final Logger LOGGER = LoggerFactory.getLogger(ApplicationServiceImpl.class);
    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationResponseDto getApplication(Long ano) {
        LOGGER.info("[getApplication] input number : {}", ano);
        Application apply = applicationRepository.findById(ano).get();
        LOGGER.info("[getApplication] input apply : {}", apply);
        ApplicationResponseDto applicationResponseDto = getApplicationResponseDto(apply);
        return applicationResponseDto;
    }

    @Override
    public ApplicationResponseDto submitApplication(ApplicationDto applicationDto) {
        LOGGER.info("[submitApplication] applicationDto : {}", applicationDto.toString());
        Application application = getApplicationEntity(applicationDto);
        Application savedApplication = applicationRepository.save(application);
        ApplicationResponseDto applicationResponseDto = getApplicationResponseDto(savedApplication);
        return applicationResponseDto;
    }

    @Override
    public ApplicationResponseDto changeContents(ChangeContentsDto changeContentsDto) throws Exception {
        Application foundApplication = applicationRepository.findById(changeContentsDto.getAno()).get();
        foundApplication.setReason(changeContentsDto.getReason());
        foundApplication.setFeedback(changeContentsDto.getFeedback());
        foundApplication.setActivity(changeContentsDto.getActivity());
        foundApplication.setAdvantage(changeContentsDto.getAdvantage());
        foundApplication.setLastWord(changeContentsDto.getLastWord());

        Application changedContents = applicationRepository.save(foundApplication);

        ApplicationResponseDto applicationResponseDto = getApplicationResponseDto(changedContents);
        return applicationResponseDto;
    }

    @Override
    public void deleteApplication(Long ano) throws Exception {
        applicationRepository.deleteById(ano);
    }
}
