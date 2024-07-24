package bitc.fullstack.sleepon.dto.intro;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class IntroDataResponseDTO {
    private IntroDataHeaderDTO header;
    private IntroDataBodyDTO body;

    @XmlElement(name = "header")
    public IntroDataHeaderDTO getHeader() {
        return header;
    }

    public void setHeader(IntroDataHeaderDTO header) {
        this.header = header;
    }

    @XmlElement(name="body")
    public IntroDataBodyDTO getBody() {
        return body;
    }

    public void setBody(IntroDataBodyDTO body) {
        this.body = body;
    }
}
