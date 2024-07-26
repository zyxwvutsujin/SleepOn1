package bitc.fullstack.sleepon.service;

import bitc.fullstack.sleepon.model.UserReservation;
import bitc.fullstack.sleepon.repository.UserReservationRepository;
import bitc.fullstack.sleepon.dto.FullDataItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private UserReservationRepository reservationRepository;

    @Autowired
    private XMLParsingService xmlParsingService;

    public void saveReservation(UserReservation reservation) {
        // XML 파싱을 통해 roomTitle 가져오기
        FullDataItemDTO dataItem = xmlParsingService.getRoomTitleByContentId(reservation.getContentId());
        if (dataItem != null) {
            reservation.setRoomTitle(dataItem.getTitle());
        }
        reservationRepository.save(reservation);
    }
}
