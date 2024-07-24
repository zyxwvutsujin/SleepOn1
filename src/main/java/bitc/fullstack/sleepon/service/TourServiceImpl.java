package bitc.fullstack.sleepon.service;

import bitc.fullstack.sleepon.dto.FullDataItemDTO;
import bitc.fullstack.sleepon.dto.FullDataResponseDTO;
import bitc.fullstack.sleepon.dto.detail.DataItemDTO;
import bitc.fullstack.sleepon.dto.detail.DataResponseDTO;
import bitc.fullstack.sleepon.mapper.LocationMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TourServiceImpl implements TourService{
    @Autowired
    LocationMapper locationMapper;

    @Override
    public List<FullDataItemDTO> getItemListUrl(String serviceUrl) throws Exception {
        List<FullDataItemDTO> itemList = new ArrayList<>();
        URL url = null;
        HttpURLConnection urlConn = null;

        try{
            url = new URL(serviceUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");

            JAXBContext jc = JAXBContext.newInstance(FullDataResponseDTO.class);
            Unmarshaller um = jc.createUnmarshaller();

            FullDataResponseDTO fullData = (FullDataResponseDTO)
 um.unmarshal(url);
            itemList = fullData.getBody().getItems().getItemList();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve data from the service URL: " + serviceUrl, e);
        }
        finally {
            if (urlConn != null) { urlConn.disconnect(); }
        }
        return itemList;
    }

    @Override
    public Map<String, String> getSigunguMap(String areaCode) {
        return locationMapper.getSigunguMapList(areaCode);
    }

    @Override
    public String getSigunguName(String areaCode, String key) {
        return locationMapper.getSigunguMapList(areaCode).get(key);
    }

    @Override
    public String getAreaName(String areaCode) throws Exception {
        return locationMapper.getAreaMap(areaCode);
    }

    @Override
    public List<DataItemDTO> getDetailItemList(String serviceUrl) throws Exception {
        List<DataItemDTO> itemList = new ArrayList<>();
        URL url = null;
        HttpURLConnection urlConn = null;

        try{
            url = new URL(serviceUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");

            JAXBContext jc = JAXBContext.newInstance(FullDataResponseDTO.class);
            Unmarshaller um = jc.createUnmarshaller();

            DataResponseDTO fullData = (DataResponseDTO)
                    um.unmarshal(url);
            itemList = fullData.getBody().getItems().getItemList();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve data from the service URL: " + serviceUrl, e);
        }
        finally {
            if (urlConn != null) { urlConn.disconnect(); }
        }
        return itemList;
    }
}
