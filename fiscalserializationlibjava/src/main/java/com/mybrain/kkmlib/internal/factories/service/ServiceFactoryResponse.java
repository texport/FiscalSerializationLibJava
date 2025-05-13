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
import com.mybrain.kkmlib.api.utils.LoggerManager;
import com.mybrain.kkmlib.internal.factories.FactoryResult;
import com.mybrain.kkmlib.internal.mappers.TicketAdTypeMapper;
import com.mybrain.kkmlib.internal.utils.ValidationCollector;
import com.mybrain.kkmlib.internal.utils.ValidationUtils;
import kz.kgdkkmproto.kkm.proto.Common;
import kz.kgdkkmproto.kkm.proto.Reginfo;
import kz.kgdkkmproto.kkm.proto.Service;

import java.util.List;
import java.util.Optional;

public class ServiceFactoryResponse {

    private final static ValidationCollector collector = new ValidationCollector();

    public FactoryResult<ServiceResponse> getResponse(Service.ServiceResponse service) {
        boolean valid = true;

        valid &= ValidationUtils.checkHasField(service.hasRegInfo(), "ServiceResponse.regInfo", ErrorCode.OFD_REG_INFO_NOT_FOUND, collector);

        RegInfoResponse regInfoResponse = service.hasRegInfo() ? createRegInfo(service.getRegInfo()) : null;

        valid &= ValidationUtils.checkNotNull(regInfoResponse, "ServiceResponse.regInfo", collector);

        Optional<List<TicketAd>> ticketAds = createTicketAds(service.getTicketAdsList());

        return valid ? FactoryResult.success(new ServiceResponse(ticketAds, regInfoResponse)) : FactoryResult.failure(collector.getErrors());
    }

    private Optional<List<TicketAd>> createTicketAds(List<Common.TicketAd> ticketAds) {
        if (ticketAds == null || ticketAds.isEmpty()) {
            return Optional.empty();
        }

        List<TicketAd> mappedAds = ticketAds.stream()
                .map(this::createTicketAd)
                .toList();

        return Optional.of(mappedAds);
    }

    private TicketAd createTicketAd(Common.TicketAd ticketAd) {
        boolean valid = true;

        valid &= ValidationUtils.checkHasField(ticketAd.hasInfo(), "TicketAd.info", ErrorCode.OFD_TICKET_AD_INFO_NOT_FOUND, collector);
        valid &= ValidationUtils.checkHasField(ticketAd.hasText(), "TicketAd.text", ErrorCode.OFD_TICKET_AD_TEXT_NOT_FOUND, collector);

        if (!valid) return null;

        TicketAdInfo ticketAdInfo = createTicketAdInfo(ticketAd.getInfo());

        if (ticketAdInfo == null) return null;

        return new TicketAd(ticketAdInfo, ticketAd.getText());
    }

    private TicketAdInfo createTicketAdInfo(Common.TicketAdInfo ticketAdInfo) {
        boolean valid = true;

        valid &= ValidationUtils.checkHasField(ticketAdInfo.hasType(), "TicketAdInfo.type", ErrorCode.OFD_TICKET_AD_TYPE_NOT_FOUND, collector);
        valid &= ValidationUtils.checkHasField(ticketAdInfo.hasVersion(), "TicketAdInfo.version", ErrorCode.OFD_TICKET_AD_VERSION_NOT_FOUND, collector);

        return valid ? new TicketAdInfo(TicketAdTypeMapper.fromProto(ticketAdInfo.getType()), ticketAdInfo.getVersion()) : null;
    }


    private RegInfoResponse createRegInfo(Service.ServiceResponse.RegInfo regInfo) {
        boolean valid = true;

        valid &= ValidationUtils.checkHasField(regInfo.hasKkm(), "RegInfo.kkm", ErrorCode.OFD_KKM_REG_INFO_NOT_FOUND, collector);
        valid &= ValidationUtils.checkHasField(regInfo.hasOrg(), "RegInfo.org", ErrorCode.OFD_ORG_REG_INFO_NOT_FOUND, collector);

        if (!valid) return null;

        KkmRegInfo kkmRegInfo = createKkmRegInfo(regInfo.getKkm());
        OrgRegInfo orgRegInfo = createOrgRegInfo(regInfo.getOrg());

        Optional<PosRegInfo> posRegInfo = regInfo.hasPos() ? createPosRegInfo(regInfo.getPos()) : Optional.empty();

        return new RegInfoResponse(kkmRegInfo, posRegInfo, orgRegInfo);
    }

    private KkmRegInfo createKkmRegInfo(Reginfo.KkmRegInfo kkmRegInfo) {
        boolean valid = true;

        valid &= ValidationUtils.checkHasField(kkmRegInfo.hasFnsKkmId(), "KkmRegInfo.kgdKkmId", ErrorCode.OFD_KKM_REG_INFO_KGD_ID_NOT_FOUND, collector);
        valid &= ValidationUtils.checkHasField(kkmRegInfo.hasSerialNumber(), "KkmRegInfo.serialNumber", ErrorCode.OFD_KKM_REG_INFO_SERIAL_NUMBER_NOT_FOUND, collector);
        valid &= ValidationUtils.checkHasField(kkmRegInfo.hasKkmId(), "KkmRegInfo.kkmId", ErrorCode.OFD_KKM_REG_INFO_KKM_ID_NOT_FOUND, collector);

        String kgdKkmId = kkmRegInfo.hasFnsKkmId() ? kkmRegInfo.getFnsKkmId() : null;
        String serialNumber = kkmRegInfo.hasSerialNumber() ? kkmRegInfo.getSerialNumber() : null;
        String kkmId = kkmRegInfo.hasKkmId() ? kkmRegInfo.getKkmId() : null;

        valid &= ValidationUtils.checkNotNull(kgdKkmId, "KkmRegInfo.kgdKkmId", collector);
        valid &= ValidationUtils.checkNotNull(serialNumber, "KkmRegInfo.serialNumber", collector);
        valid &= ValidationUtils.checkNotNull(kkmId, "KkmRegInfo.kkmId", collector);

        return valid ? new KkmRegInfo(kgdKkmId, serialNumber, kkmId) : null;
    }

    private OrgRegInfo createOrgRegInfo(Reginfo.OrgRegInfo orgRegInfo) {
        boolean valid = true;

        valid &= ValidationUtils.checkHasField(orgRegInfo.hasTitle(), "OrgRegInfo.title", ErrorCode.OFD_ORG_REG_INFO_TITLE_NOT_FOUND, collector);
        valid &= ValidationUtils.checkHasField(orgRegInfo.hasAddress(), "OrgRegInfo.address", ErrorCode.OFD_ORG_REG_INFO_ADDRESS_NOT_FOUND, collector);
        valid &= ValidationUtils.checkHasField(orgRegInfo.hasInn(), "OrgRegInfo.iin", ErrorCode.OFD_ORG_REG_INFO_IIN_NOT_FOUND, collector);
        valid &= ValidationUtils.checkHasField(orgRegInfo.hasOkved(), "OrgRegInfo.oked", ErrorCode.OFD_ORG_REG_INFO_OKED_NOT_FOUND, collector);

        String title = orgRegInfo.hasTitle() ? orgRegInfo.getTitle() : null;
        String address = orgRegInfo.hasAddress() ? orgRegInfo.getAddress() : null;
        String inn = orgRegInfo.hasInn() ? orgRegInfo.getInn() : null;
        String oked = orgRegInfo.hasOkved() ? orgRegInfo.getOkved() : null;

        valid &= ValidationUtils.checkNotNull(title, "OrgRegInfo.title", collector);
        valid &= ValidationUtils.checkNotNull(address, "OrgRegInfo.address", collector);
        valid &= ValidationUtils.checkNotNull(inn, "OrgRegInfo.iin", collector);
        valid &= ValidationUtils.checkNotNull(oked, "OrgRegInfo.oked", collector);

        return valid ? new OrgRegInfo(title, address, inn, oked) : null;
    }

    private Optional<PosRegInfo> createPosRegInfo(Reginfo.PosRegInfo posRegInfo) {
        boolean valid = true;

        valid &= ValidationUtils.checkHasField(posRegInfo.hasTitle(), "PosRegInfo.title", ErrorCode.OFD_POS_REG_INFO_TITLE_NOT_FOUND, collector);
        valid &= ValidationUtils.checkHasField(posRegInfo.hasAddress(), "PosRegInfo.address", ErrorCode.OFD_POS_REG_INFO_ADDRESS_NOT_FOUND, collector);

        String title = posRegInfo.hasTitle() ? posRegInfo.getTitle() : null;
        String address = posRegInfo.hasAddress() ? posRegInfo.getAddress() : null;

        valid &= ValidationUtils.checkNotNull(title, "PosRegInfo.title", collector);
        valid &= ValidationUtils.checkNotNull(address, "PosRegInfo.address", collector);

        return valid ? Optional.of(new PosRegInfo(title, address)) : Optional.empty();
    }
}
