package bitc.fullstack.sleepon.controller;

import bitc.fullstack.sleepon.dto.FullDataItemDTO;
import bitc.fullstack.sleepon.dto.infor.DataComItemDTO;
import bitc.fullstack.sleepon.dto.event.FullEventDataItemDTO;
import bitc.fullstack.sleepon.model.SleepOnUser;
import bitc.fullstack.sleepon.model.UserReservation;
import bitc.fullstack.sleepon.service.TourService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Value("${SleepOn.service.Infor.Url}")
    private String APIInforUrl;

    @Value("${SleepOn.service.detail.Url}")
    private String APIDetailUrl;

    @Value("${SleepOnEvent.service.Url}")
    private String eventApiurl;

    @RequestMapping(value = {"", "/"})
    public String SleepOnService(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "page/SleepOnMain";
    }

    @GetMapping("/login")
    public String SleepOnLogin() throws Exception {
        return "member/login";
    }

    @GetMapping("/register")
    public String SleepOnRegister() throws Exception {
        return "member/register";
    }

    @GetMapping("/logout")
    public String SleepOnLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/SleepOn";
    }

    @ResponseBody
    @RequestMapping("/selectArea")
    public Object SleepOnService(@RequestParam("areaCode") String areaCode, @RequestParam("sigunguCode") String sigunguCode, @RequestParam("arrange") String arrange, @RequestParam("numOfRows") String numOfRows, @RequestParam("pageNo") String pageNo) throws Exception {
        System.out.println("main 페이지에서 선택 : " + areaCode);

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

    @ResponseBody
    @RequestMapping("/FestivalArea")
    public Object SearchFestival(@RequestParam("areaCode") String areaCode) throws Exception {
        String dateFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("\n 날짜 : " + dateFormat);

        String opt1 = "?areaCode=";
        String opt2 = "&serviceKey=";
        String opt3 = "&numOfRows=200&pageNo=1&MobileOS=ETC&MobileApp=AppTest&listYN=Y&arrange=R&eventStartDate=" + dateFormat;

        List<FullEventDataItemDTO> itemList = tourService.getEventItemListUrl(eventApiurl + opt1 + areaCode + opt2 + APIkey + opt3);

        return itemList;
    }

    @RequestMapping("/SearchFestival")
    public String SearchFestival(@RequestParam("areaCode") String areaCode, HttpSession session) throws Exception {
        System.out.println("searchFestival : " + areaCode);
        String areaName = tourService.getAreaName(areaCode);
        session.setAttribute("areaCode", areaCode);
        session.setAttribute("areaName", areaName);

        return "redirect:/SleepOn/Festival";
    }

    @RequestMapping("/Festival")
    public ModelAndView AreaFestival(HttpSession session, HttpServletRequest request, Model model) throws Exception {
        addSessionAttributesToModel(request, model);
        ModelAndView mv = new ModelAndView("page/SleepOnFestival");

        String areaCode = (String) session.getAttribute("areaCode");
        mv.addObject("areaCode", areaCode);

        String areaName = (String) session.getAttribute("areaName");
        mv.addObject("areaName", areaName);

        return mv;
    }

    @ResponseBody
    @RequestMapping("/SigunguSelect")
    public Map<String, String> selectSigungu(@RequestParam("areaCode") String areaCode) throws Exception {
        System.out.println("\nsearch 창에 지역 선택 : " + areaCode + "\n");

        Map<String, String> sigunguMap = tourService.getSigunguMap(areaCode);

        return sigunguMap;
    }

    @RequestMapping("/Search")
    public String SleepOnSearch(@RequestParam("areaCode") String areaCode, @RequestParam("sigunguCode") String sigunguCode, @RequestParam("checkIn") String checkIn, @RequestParam("checkOut") String checkOut, @RequestParam("userCnt") String userCnt, HttpSession session) throws Exception {

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
    public ModelAndView SleepOnList(HttpSession session, HttpServletRequest request, Model model) throws Exception {
        addSessionAttributesToModel(request, model);
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

    @RequestMapping("/Detail")
    public String SleepOnDetail(@RequestParam("contentId") String contentId, @RequestParam("userCnt") String userCnt, @RequestParam("checkIn") String checkIn, @RequestParam("checkOut") String checkOut, HttpSession session) throws Exception {
        session.setAttribute("contentId", contentId);
        session.setAttribute("userCnt", userCnt);
        session.setAttribute("checkIn", checkIn);
        session.setAttribute("checkOut", checkOut);

        return "redirect:/SleepOn/HotelDetail";
    }

    @RequestMapping("/HotelDetail")
    public ModelAndView SleepOnDetail(HttpSession session, HttpServletRequest request, Model model) throws Exception {
        addSessionAttributesToModel(request, model);
        ModelAndView mv = new ModelAndView("page/SleepOnHotelDetail");

        String contentId = (String) session.getAttribute("contentId");
        String userCnt = (String) session.getAttribute("userCnt");
        String checkIn = (String) session.getAttribute("checkIn");
        String checkOut = (String) session.getAttribute("checkOut");

        mv.addObject("contentId", contentId);
        mv.addObject("userCnt", userCnt);
        mv.addObject("checkIn", checkIn);
        mv.addObject("checkOut", checkOut);

        return mv;
    }

    @RequestMapping("/HotelDetailInfo")
    @ResponseBody
    public List<DataComItemDTO> getHotelDetailInfo(@RequestParam("contentId") String contentId) throws Exception {
        String opt1 = "?contentId=";
        String opt2 = "&ServiceKey=";
        String opt3 = "&MobileOS=ETC&MobileApp=AppTest";

        String serviceUrl = APIInforUrl + opt1 + contentId + opt2 + APIkey + opt3;
        List<DataComItemDTO> infoList = tourService.getInforItemList(serviceUrl);
        if (infoList == null) {
            throw new Exception("Failed to retrieve hotel information");
        }
        return infoList;
    }

    @GetMapping("/myPage")
    public String myPage(HttpServletRequest request, Model model) {
        addSessionAttributesToModel(request, model);
        return model.containsAttribute("user") ? "member/myPage" : "redirect:/SleepOn/login";
    }

    private void addSessionAttributesToModel(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public String reserve(
            @RequestParam("contentId") String contentId,
            @RequestParam("checkIn") String checkIn,
            @RequestParam("checkOut") String checkOut,
            @RequestParam("roomtitle") String roomtitle,
            @RequestParam("userCnt") int userCnt,
            HttpSession session) {

        SleepOnUser user = (SleepOnUser) session.getAttribute("user");

        if (user == null) {
            return "redirect:/SleepOn/login";
        }

        UserReservation reservation = new UserReservation();
        reservation.setUser(user);
        reservation.setReservData(LocalDateTime.now());
        reservation.setCheckinTime(checkIn);
        reservation.setCheckoutTime(checkOut);
        reservation.setContentId(contentId);
        reservation.setReservCancel('N');
        reservation.setRoomTitle(roomtitle);
        reservation.setUserCnt(userCnt);

        tourService.saveReservation(reservation);

        return "redirect:/SleepOn/reservationSuccess";
    }



    @RequestMapping("/reservationSuccess")
    public String reservationSuccess() {
        return "member/reservationSuccess";
    }
}
