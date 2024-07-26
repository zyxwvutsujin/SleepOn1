package bitc.fullstack.sleepon.service;

import bitc.fullstack.sleepon.dto.FullDataResponseDTO;
import bitc.fullstack.sleepon.dto.FullDataItemDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;

@Service
public class XMLParsingService {

    @Value("${SleepOn.service.detail.Url}")
    private String detailUrl;

    @Value("${SleepOn.service.key}")
    private String serviceKey;

    public FullDataItemDTO getRoomTitleByContentId(String contentId) {
        String url = String.format("%s?serviceKey=%s&MobileOS=ETC&MobileApp=AppTest&contentId=%s&contentTypeId=32&numOfRows=10&pageNo=1",
                detailUrl, serviceKey, contentId);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FullDataResponseDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(response);
            FullDataResponseDTO responseDTO = (FullDataResponseDTO) unmarshaller.unmarshal(reader);

            if (responseDTO != null && responseDTO.getBody() != null &&
                    responseDTO.getBody().getItems() != null && !responseDTO.getBody().getItems().getItemList().isEmpty()) {
                return responseDTO.getBody().getItems().getItemList().get(0);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
