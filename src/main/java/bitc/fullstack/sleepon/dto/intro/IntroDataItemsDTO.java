package bitc.fullstack.sleepon.dto.intro;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name="items")
public class IntroDataItemsDTO {
    private List<IntroDataItemDTO> itemList;

    @XmlElement(name="item")
    public List<IntroDataItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<IntroDataItemDTO> itemList) {
        this.itemList = itemList;
    }
}
