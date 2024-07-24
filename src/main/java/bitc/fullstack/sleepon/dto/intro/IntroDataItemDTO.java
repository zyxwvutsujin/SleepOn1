package bitc.fullstack.sleepon.dto.intro;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class IntroDataItemDTO {
    private String contentid;
    private String contenttypeid;
    private String goodstay;
    private String benikia;
    private String hanok;
    private String roomcount;
    private String roomtype;
    private String refundregulation;
    private String checkintime;
    private String checkouttime;
    private String chkcooking;
    private String seminar;
    private String sports;
    private String sauna;
    private String beauty;
    private String beverage;
    private String karaoke;
    private String barbecue;
    private String campfire;
    private String bicycle;
    private String fitness;
    private String publicpc;
    private String publicbath;
    private String subfacility;
    private String foodplace;
    private String reservationurl;
    private String pickup;
    private String infocenterlodging;
    private String parkinglodging;
    private String reservationlodging;
    private String scalelodging;
    private String accomcountlodging;

    // Getters and Setters
    @XmlElement
    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    @XmlElement
    public String getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(String contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    @XmlElement
    public String getGoodstay() {
        return goodstay;
    }

    public void setGoodstay(String goodstay) {
        this.goodstay = goodstay;
    }

    @XmlElement
    public String getBenikia() {
        return benikia;
    }

    public void setBenikia(String benikia) {
        this.benikia = benikia;
    }

    @XmlElement
    public String getHanok() {
        return hanok;
    }

    public void setHanok(String hanok) {
        this.hanok = hanok;
    }

    @XmlElement
    public String getRoomcount() {
        return roomcount;
    }

    public void setRoomcount(String roomcount) {
        this.roomcount = roomcount;
    }

    @XmlElement
    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    @XmlElement
    public String getRefundregulation() {
        return refundregulation;
    }

    public void setRefundregulation(String refundregulation) {
        this.refundregulation = refundregulation;
    }

    @XmlElement
    public String getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(String checkintime) {
        this.checkintime = checkintime;
    }

    @XmlElement
    public String getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(String checkouttime) {
        this.checkouttime = checkouttime;
    }

    @XmlElement
    public String getChkcooking() {
        return chkcooking;
    }

    public void setChkcooking(String chkcooking) {
        this.chkcooking = chkcooking;
    }

    @XmlElement
    public String getSeminar() {
        return seminar;
    }

    public void setSeminar(String seminar) {
        this.seminar = seminar;
    }

    @XmlElement
    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    @XmlElement
    public String getSauna() {
        return sauna;
    }

    public void setSauna(String sauna) {
        this.sauna = sauna;
    }

    @XmlElement
    public String getBeauty() {
        return beauty;
    }

    public void setBeauty(String beauty) {
        this.beauty = beauty;
    }

    @XmlElement
    public String getBeverage() {
        return beverage;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }

    @XmlElement
    public String getKaraoke() {
        return karaoke;
    }

    public void setKaraoke(String karaoke) {
        this.karaoke = karaoke;
    }

    @XmlElement
    public String getBarbecue() {
        return barbecue;
    }

    public void setBarbecue(String barbecue) {
        this.barbecue = barbecue;
    }

    @XmlElement
    public String getCampfire() {
        return campfire;
    }

    public void setCampfire(String campfire) {
        this.campfire = campfire;
    }

    @XmlElement
    public String getBicycle() {
        return bicycle;
    }

    public void setBicycle(String bicycle) {
        this.bicycle = bicycle;
    }

    @XmlElement
    public String getFitness() {
        return fitness;
    }

    public void setFitness(String fitness) {
        this.fitness = fitness;
    }

    @XmlElement
    public String getPublicpc() {
        return publicpc;
    }

    public void setPublicpc(String publicpc) {
        this.publicpc = publicpc;
    }

    @XmlElement
    public String getPublicbath() {
        return publicbath;
    }

    public void setPublicbath(String publicbath) {
        this.publicbath = publicbath;
    }

    @XmlElement
    public String getSubfacility() {
        return subfacility;
    }

    public void setSubfacility(String subfacility) {
        this.subfacility = subfacility;
    }

    @XmlElement
    public String getFoodplace() {
        return foodplace;
    }

    public void setFoodplace(String foodplace) {
        this.foodplace = foodplace;
    }

    @XmlElement
    public String getReservationurl() {
        return reservationurl;
    }

    public void setReservationurl(String reservationurl) {
        this.reservationurl = reservationurl;
    }

    @XmlElement
    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    @XmlElement
    public String getInfocenterlodging() {
        return infocenterlodging;
    }

    public void setInfocenterlodging(String infocenterlodging) {
        this.infocenterlodging = infocenterlodging;
    }

    @XmlElement
    public String getParkinglodging() {
        return parkinglodging;
    }

    public void setParkinglodging(String parkinglodging) {
        this.parkinglodging = parkinglodging;
    }

    @XmlElement
    public String getReservationlodging() {
        return reservationlodging;
    }

    public void setReservationlodging(String reservationlodging) {
        this.reservationlodging = reservationlodging;
    }

    @XmlElement
    public String getScalelodging() {
        return scalelodging;
    }

    public void setScalelodging(String scalelodging) {
        this.scalelodging = scalelodging;
    }

    @XmlElement
    public String getAccomcountlodging() {
        return accomcountlodging;
    }

    public void setAccomcountlodging(String accomcountlodging) {
        this.accomcountlodging = accomcountlodging;
    }
}