package main.service;



import main.api.request.SettingsRequest;
import main.api.response.SettingsResponse;
import main.model.Enum.GlobalSettingValue;
import main.model.GlobalSetting;
import main.repository.GlobalSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private GlobalSettingRepository globalSettingRepository;

    public SettingsResponse getGlobalSettings() {

        SettingsResponse settingsResponse = new SettingsResponse();

        settingsResponse.setMultiuserMode((globalSettingRepository.findAllByCode("MULTIUSER_MODE").getValue().equals(GlobalSettingValue.YES)) ? true : false);
        settingsResponse.setPostPremoderation((globalSettingRepository.findAllByCode("POST_PREMODERATION").getValue().equals(GlobalSettingValue.YES)) ? true : false);
        settingsResponse.setStatisticsIsPublic((globalSettingRepository.findAllByCode("STATISTICS_IS_PUBLIC").getValue().equals(GlobalSettingValue.YES)) ? true : false);
        return settingsResponse;
    }

    public void setGlobalSettings(SettingsRequest settingsRequest) {

        GlobalSetting multiuserMode = globalSettingRepository.findAllByCode("MULTIUSER_MODE");
        multiuserMode.setValue(settingsRequest.isMultiuserMode() ? GlobalSettingValue.YES : GlobalSettingValue.NO);
        globalSettingRepository.save(multiuserMode);

        GlobalSetting postPremoderation = globalSettingRepository.findAllByCode("POST_PREMODERATION");
        postPremoderation.setValue(settingsRequest.isPostPremoderation() ? GlobalSettingValue.YES : GlobalSettingValue.NO);
        globalSettingRepository.save(postPremoderation);

        GlobalSetting statisticsIsPublic = globalSettingRepository.findAllByCode("STATISTICS_IS_PUBLIC");
        statisticsIsPublic.setValue(settingsRequest.isStatisticsIsPublic() ? GlobalSettingValue.YES : GlobalSettingValue.NO);
        globalSettingRepository.save(statisticsIsPublic);
    }
}
