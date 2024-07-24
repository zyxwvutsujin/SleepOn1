package bitc.fullstack.sleepon.service;

import bitc.fullstack.sleepon.dto.FullDataItemDTO;
import bitc.fullstack.sleepon.dto.detail.DataItemDTO;

import java.util.List;
import java.util.Map;

public interface TourService {
    List<FullDataItemDTO> getItemListUrl(String serviceUrl) throws Exception;

    Map<String, String> getSigunguMap(String areaCode) throws Exception;
    String getSigunguName(String areaCode, String key) throws Exception;
    String getAreaName(String areaCode) throws Exception;

    List<DataItemDTO> getDetailItemList(String serviceUrl) throws Exception;
}
