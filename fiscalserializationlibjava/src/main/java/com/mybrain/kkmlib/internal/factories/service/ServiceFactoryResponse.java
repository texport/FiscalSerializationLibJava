package com.mybrain.kkmlib.internal.factories.service;

import com.mybrain.kkmlib.api.errors.ErrorCode;
import com.mybrain.kkmlib.api.errors.KkmLibException;
import com.mybrain.kkmlib.api.models.reginfo.KkmRegInfo;
import com.mybrain.kkmlib.api.models.reginfo.OrgRegInfo;
import com.mybrain.kkmlib.api.models.reginfo.PosRegInfo;
import com.mybrain.kkmlib.api.models.ticketad.TicketAd;
import com.mybrain.kkmlib.api.models.ticketad.TicketAdInfo;
import com.mybrain.kkmlib.api.response.models.RegInfoResponse;
import com.mybrain.kkmlib.api.response.models.ServiceResponse;
import com.mybrain.kkmlib.internal.mappers.TicketAdTypeMapper;
import kz.kgdkkmproto.kkm.proto.Common;
import kz.kgdkkmproto.kkm.proto.Reginfo;
import kz.kgdkkmproto.kkm.proto.Service;

import java.util.List;
import java.util.Optional;

public class ServiceFactoryResponse {

    public static ServiceResponse getResponse(Service.ServiceResponse service) {
        if (!service.hasRegInfo()) {
            throw new KkmLibException(ErrorCode.OFD_REG_INFO_NOT_FOUND);
        }

        RegInfoResponse regInfoResponse = createRegInfo(service.getRegInfo());

        List<Common.TicketAd> ticketAdsListPayload = service.getTicketAdsList();
        Optional<List<TicketAd>> ticketAds = createTicketAds(ticketAdsListPayload);

        return new ServiceResponse(ticketAds, regInfoResponse);
    }

    private static Optional<List<TicketAd>> createTicketAds(List<Common.TicketAd> ticketAds) {
        if (ticketAds == null || ticketAds.isEmpty()) {
            return Optional.empty();
        }

        List<TicketAd> mappedAds = ticketAds.stream()
                .map(ServiceFactoryResponse::createTicketAd)
                .toList();
        return Optional.of(mappedAds);
    }

    private static TicketAd createTicketAd(Common.TicketAd ticketAd) {
        if (!ticketAd.hasInfo()) {
            throw new KkmLibException(ErrorCode.OFD_TICKET_AD_INFO_NOT_FOUND);
        }

        TicketAdInfo ticketAdInfo = createTicketAdInfo(ticketAd.getInfo());

        if (!ticketAd.hasText()) {
            throw new KkmLibException(ErrorCode.OFD_TICKET_AD_TEXT_NOT_FOUND);
        }

        return new TicketAd(ticketAdInfo, ticketAd.getText());
    }

    private static TicketAdInfo createTicketAdInfo(Common.TicketAdInfo ticketAdInfo) {
        if (!ticketAdInfo.hasType()) {
            throw new KkmLibException(ErrorCode.OFD_TICKET_AD_TYPE_NOT_FOUND);
        }

        // TODO: Возможно сделать валидацию, версия != 0
        if (!ticketAdInfo.hasVersion()) {
            throw new KkmLibException(ErrorCode.OFD_TICKET_AD_VERSION_NOT_FOUND);
        }

        return new TicketAdInfo(TicketAdTypeMapper.fromProto(ticketAdInfo.getType()), ticketAdInfo.getVersion());
    }

    private static RegInfoResponse createRegInfo(Service.ServiceResponse.RegInfo regInfo) {
        if (!regInfo.hasKkm()) {
            throw new KkmLibException(ErrorCode.OFD_KKM_REG_INFO_NOT_FOUND);
        }

        if (!regInfo.hasOrg()) {
            throw new KkmLibException(ErrorCode.OFD_ORG_REG_INFO_NOT_FOUND);
        }

        KkmRegInfo kkmRegInfo = createKkmRegInfo(regInfo.getKkm());
        OrgRegInfo orgRegInfo = createOrgRegInfo(regInfo.getOrg());
        Optional<PosRegInfo> posRegInfo = createPosRegInfo(regInfo.getPos());

        return new RegInfoResponse(kkmRegInfo, posRegInfo, orgRegInfo);
    }

    private static KkmRegInfo createKkmRegInfo(Reginfo.KkmRegInfo kkmRegInfo) {
        if (!kkmRegInfo.hasFnsKkmId()) {
            throw new KkmLibException(ErrorCode.OFD_KKM_REG_INFO_KGD_ID_NOT_FOUND);
        }

        if (!kkmRegInfo.hasSerialNumber()) {
            throw new KkmLibException(ErrorCode.OFD_KKM_REG_INFO_SERIAL_NUMBER_NOT_FOUND);
        }

        if (!kkmRegInfo.hasKkmId()) {
            throw new KkmLibException(ErrorCode.OFD_KKM_REG_INFO_KKM_ID_NOT_FOUND);
        }

        return new KkmRegInfo(kkmRegInfo.getFnsKkmId(), kkmRegInfo.getSerialNumber(), kkmRegInfo.getKkmId());
    }

    private static OrgRegInfo createOrgRegInfo(Reginfo.OrgRegInfo orgRegInfo) {
        if (!orgRegInfo.hasTitle()) {
            throw new KkmLibException(ErrorCode.OFD_ORG_REG_INFO_TITLE_NOT_FOUND);
        }

        if (!orgRegInfo.hasAddress()) {
            throw new KkmLibException(ErrorCode.OFD_ORG_REG_INFO_ADDRESS_NOT_FOUND);
        }

        if (!orgRegInfo.hasInn()) {
            throw new KkmLibException(ErrorCode.OFD_ORG_REG_INFO_IIN_NOT_FOUND);
        }

        // TODO: Обязательно проверить, что ОФД присылает не пустую строку
        if (!orgRegInfo.hasOkved()) {
            throw new KkmLibException(ErrorCode.OFD_ORG_REG_INFO_OKED_NOT_FOUND);
        }

        return new OrgRegInfo(orgRegInfo.getTitle(), orgRegInfo.getAddress(), orgRegInfo.getInn(), orgRegInfo.getOkved());
    }

    private static Optional<PosRegInfo> createPosRegInfo(Reginfo.PosRegInfo posRegInfo) {
        if (!posRegInfo.hasTitle()) {
            throw new KkmLibException(ErrorCode.OFD_POS_REG_INFO_TITLE_NOT_FOUND);
        }

        if (!posRegInfo.hasAddress()) {
            throw new KkmLibException(ErrorCode.OFD_POS_REG_INFO_ADDRESS_NOT_FOUND);
        }

        PosRegInfo optionalPosRegInfo = new PosRegInfo(posRegInfo.getTitle(), posRegInfo.getAddress());

        return Optional.of(optionalPosRegInfo);
    }
}
