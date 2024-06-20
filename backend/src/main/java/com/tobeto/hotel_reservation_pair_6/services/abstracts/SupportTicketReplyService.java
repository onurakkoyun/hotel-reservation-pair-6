package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.services.dtos.supportTicketReplyDtos.requests.CreateSupportTicketReplyRequest;

public interface SupportTicketReplyService {
    Result createSupportReplyRequest(CreateSupportTicketReplyRequest request);
}
