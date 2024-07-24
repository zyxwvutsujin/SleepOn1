package bitc.fullstack.sleepon.controller;

import bitc.fullstack.sleepon.dto.FullDataItemDTO;
import bitc.fullstack.sleepon.mapper.LocationMapper;
import bitc.fullstack.sleepon.service.TourService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/SleepOn")
public class TourController {

    @Autowired
    TourService tourService;

    @Value("${SleepOn.service.key}")
    private String APIkey;

    @Value("${SleepOn.service.Url}")
    private String APIUrl;

    // 숙소 기본 정부 ( 시설 정보, 이용 안내)
    @Value("${SleepOn.service.Intro.Url}")
    private String APIIntroUrl;

    // 숙소 세부 정부 (객실, 가격)
    @Value("${SleepOn.service.detail.Url}")
    private String APIDetailUrl;

    @RequestMapping(value = {"", "/"})
    public String SleepOnService() throws Exception {
        return "page/SleepOnMain";
    }

    @ResponseBody
    @RequestMapping("/selectArea")
    public Object SleepOnService(@RequestParam("areaCode") String areaCode, @RequestParam("sigunguCode") String sigunguCode, @RequestParam("arrange") String arrange, @RequestParam("numOfRows") String numOfRows, @RequestParam("pageNo") String pageNo) throws Exception {
        System.out.println("main 페이지에서 선택 : " + areaCode);
//http://apis.data.go.kr/B551011/KorService1/searchStay1?areaCode=&sigunguCode=&ServiceKey=인증키&listYN=Y&MobileOS=ETC&MobileApp=AppTest&arrange=A&numOfRows=12&pageNo=1
        String opt1 = "?areaCode="; // 지역코드
        String opt2 = "&sigunguCode="; // 시군구코드
        String opt3 = "&ServiceKey=";
        String opt4 = "&listYN=Y&MobileOS=ETC&MobileApp=AppTest";
        String opt5 = "&arrange="; // 정렬순(사진있는거만)
        String opt6 = "&numOfRows=";
        String opt7 = "&pageNo=";

        List<FullDataItemDTO> itemList = tourService.getItemListUrl(APIUrl + opt1 + areaCode + opt2 + sigunguCode + opt3 + APIkey + opt4 + opt5 + arrange + opt6 + numOfRows + opt7 + pageNo);

        return itemList;
    }

    // 지역 선택시 시군구
    @ResponseBody
    @RequestMapping("/SigunguSelect")
    public Map<String, String> selectSigungu(@RequestParam("areaCode") String areaCode) throws Exception {
        System.out.println("\nsearch 창에 지역 선택 : " + areaCode + "\n");

        Map<String, String> sigunguMap = tourService.getSigunguMap(areaCode);

        return sigunguMap;
    }

    // 검색 창에 지역, 시군구, 날짜, 인원 선택하면 세션 저장하여 리다이렉트
    @RequestMapping("/Search")
    public String SleepOnSearch(@RequestParam("areaCode") String areaCode, @RequestParam("sigunguCode") String sigunguCode, @RequestParam("checkIn") String checkIn, @RequestParam("checkOut") String checkOut, @RequestParam("userCnt") String userCnt, HttpSession session) throws Exception {

        // 값 저장
        session.setAttribute("areaCode", areaCode);
        session.setAttribute("sigunguCode", sigunguCode);
        session.setAttribute("checkIn", checkIn);
        session.setAttribute("checkOut", checkOut);
        session.setAttribute("userCnt", userCnt);

        String areaName = tourService.getAreaName(areaCode);
        String sigunguName = tourService.getSigunguName(areaCode, sigunguCode);

        session.setAttribute("areaName", areaName);
        session.setAttribute("sigunguName", sigunguName);

        return "redirect:/SleepOn/SleepOnList";
    }

    @ResponseBody
    @RequestMapping("/SleepOnList")
    public ModelAndView SleepOnList(HttpSession session) throws Exception {
        // 세션에서 값 가져오기
        String areaCode = (String) session.getAttribute("areaCode");
        String sigunguCode = (String) session.getAttribute("sigunguCode");
        String checkIn = (String) session.getAttribute("checkIn");
        String checkOut = (String) session.getAttribute("checkOut");
        String userCnt = (String) session.getAttribute("userCnt");
        String areaName = (String) session.getAttribute("areaName");
        String sigunguName = (String) session.getAttribute("sigunguName");

        ModelAndView mv = new ModelAndView("page/SleepOnList");
        mv.addObject("areaCode", areaCode);
        mv.addObject("sigunguCode", sigunguCode);
        mv.addObject("checkIn", checkIn);
        mv.addObject("checkOut", checkOut);
        mv.addObject("userCnt", userCnt);
        mv.addObject("areaName", areaName);
        mv.addObject("sigunguName", sigunguName);

        return mv;
    }
}
