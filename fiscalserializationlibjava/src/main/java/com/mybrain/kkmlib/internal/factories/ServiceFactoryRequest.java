package com.mybrain.kkmlib.internal.factories;

import com.mybrain.kkmlib.api.models.reginfo.KkmRegInfo;
import com.mybrain.kkmlib.api.models.reginfo.OrgRegInfo;
import com.mybrain.kkmlib.api.request.models.OfflinePeriodRequest;
import com.mybrain.kkmlib.api.request.models.RegInfoRequest;
import com.mybrain.kkmlib.api.request.models.ServiceRequest;
import com.mybrain.kkmlib.internal.mappers.DateTimeMapper;
import com.mybrain.kkmlib.internal.utils.ValidationUtils;
import kz.kgdkkmproto.kkm.proto.Reginfo;
import kz.kgdkkmproto.kkm.proto.Service;

import java.time.LocalDateTime;

public class ServiceFactoryRequest {

    /**
     * Метод для создания и возвращения сервисной части запроса в формате протокола
     * */
    public static Service.ServiceRequest createRequest(ServiceRequest serviceRequest) {
        return Service.ServiceRequest.newBuilder()
                .setOfflinePeriod(createProtoOfflinePeriod(serviceRequest.offlinePeriod()))
                .setRegInfo(createProtoRegInfo(serviceRequest.regInfo()))
                .setGetRegInfo(true)
                .build();
    }

    /**
     * Метод для создания и возвращения структуры 'RegInfo' в формате протокола
     * */
    private static Service.ServiceRequest.RegInfo createProtoRegInfo(RegInfoRequest regInfo) {
        ValidationUtils.requireNotNull(regInfo, "Объект 'regInfo' в 'RegInfoRequest'");
        ValidationUtils.requireNotNull(regInfo.kkm(), "Объект 'kkm' внутри 'regInfo' в 'RegInfoRequest'");
        ValidationUtils.requireNotNull(regInfo.org(), "Объект 'org' внутри 'regInfo' в 'RegInfoRequest'");

        return Service.ServiceRequest.RegInfo.newBuilder()
                .setKkm(createProtoKkmRegInfo(regInfo.kkm()))
                .setOrg(createProtoOrgRegInfo(regInfo.org()))
                .build();
    }

    /**
     * Метод для создания и возвращения структуры 'KkmRegInfo' в формате протокола
     * */
    private static Reginfo.KkmRegInfo createProtoKkmRegInfo(KkmRegInfo kkmRegInfo) {
        //Проверка kgdKkmId
        ValidationUtils.requireNotNull(kkmRegInfo.kgdKkmId(),"Поле 'kgdKkmId' в 'KkmRegInfo'");
        ValidationUtils.requireSpacesBeginEnd(kkmRegInfo.kgdKkmId(), "Поле 'kgdKkmId' в 'KkmRegInfo'");
        ValidationUtils.requireStringLength(kkmRegInfo.kgdKkmId(), 12, "Поле 'kgdKkmId' в 'KkmRegInfo'");
        ValidationUtils.requireStringNumber(kkmRegInfo.kgdKkmId(), "Поле 'kgdKkmId' в 'KkmRegInfo'");

        //Проверка serialNumber
        ValidationUtils.requireNotNull(kkmRegInfo.serialNumber(), "Поле 'serialNumber' в 'KkmRegInfo'");
        ValidationUtils.requireSpacesBeginEnd(kkmRegInfo.serialNumber(), "Поле 'serialNumber' в 'KkmRegInfo'");
        ValidationUtils.requireNotEmpty(kkmRegInfo.serialNumber(), "Поле 'serialNumber' в 'KkmRegInfo'");
        ValidationUtils.requireNotLongerCould(kkmRegInfo.serialNumber(), 255, "Поле 'serialNumber' в 'KkmRegInfo'");
        ValidationUtils.requireNotSpaces(kkmRegInfo.serialNumber(), "Поле 'serialNumber' в 'KkmRegInfo'");

        if (kkmRegInfo.serialNumber().matches("^[^\\p{L}\\p{N}]+$")) {
            throw new IllegalArgumentException("Поле 'serialNumber' в 'KkmRegInfo' не может состоять только из специальных символов.");
        }

        //Проверка kkmId
        if (kkmRegInfo.kkmId() == null) {
            throw new IllegalArgumentException("Поле 'kkmId' в 'KkmRegInfo' не может быть null.");
        }
        if (!kkmRegInfo.kkmId().equals(kkmRegInfo.kkmId().trim())) {
            throw new IllegalArgumentException("Поле 'kkmId' в 'KkmRegInfo' не должно содержать пробелы в начале или в конце.");
        }
        if (kkmRegInfo.kkmId().isEmpty()) {
            throw new IllegalArgumentException("Поле 'kkmId' в 'KkmRegInfo' должно содержать хотя бы 1 символ.");
        }
        if (kkmRegInfo.kkmId().length() > 16) {
            throw new IllegalArgumentException("Поле 'kkmId' в 'KkmRegInfo' не должно превышать 16 символов.");
        }
        if (kkmRegInfo.kkmId().matches("^ +$")) {
            throw new IllegalArgumentException("Поле 'kkmId' в 'KkmRegInfo' не может состоять только из пробелов.");
        }
        if (!kkmRegInfo.kkmId().matches("^\\d+$")) {
            throw new IllegalArgumentException("Поле 'kkmId' в 'KkmRegInfo' должно состоять только из цифр.");
        }

        return Reginfo.KkmRegInfo.newBuilder()
                .setFnsKkmId(kkmRegInfo.kgdKkmId())
                .setSerialNumber(kkmRegInfo.serialNumber())
                .setKkmId(kkmRegInfo.kkmId())
                .build();
    }

    /**
     * Метод для создания и возвращения структуры 'OrgRegInfo' в формате протокола
     * */
    private static Reginfo.OrgRegInfo createProtoOrgRegInfo(OrgRegInfo orgRegInfo) {
        //Проверка title
        if (orgRegInfo.title() == null) {
            throw new IllegalArgumentException("Поле 'title' в 'orgRegInfo' не может быть null.");
        }
        if (!orgRegInfo.title().equals(orgRegInfo.title().trim())) {
            throw new IllegalArgumentException("Поле 'title' в 'orgRegInfo' не должно содержать пробелы в начале или в конце.");
        }
        if (orgRegInfo.title().isEmpty()) {
            throw new IllegalArgumentException("Поле 'title' в 'orgRegInfo' должно содержать хотя бы 1 символ.");
        }
        if (orgRegInfo.title().length() > 255) {
            throw new IllegalArgumentException("Поле 'title' в 'orgRegInfo' не должно превышать 255 символов.");
        }
        if (orgRegInfo.title().matches("^\\d+$")) {
            throw new IllegalArgumentException("Поле 'title' в 'orgRegInfo' не может состоять только из цифр.");
        }
        if (orgRegInfo.title().matches("^[^\\p{L}\\p{N}]+$")) {
            throw new IllegalArgumentException("Поле 'title' в 'orgRegInfo' не может состоять только из специальных символов.");
        }

        //Проверка address
        if (orgRegInfo.address() == null) {
            throw new IllegalArgumentException("Поле 'address' в 'orgRegInfo' не может быть null.");
        }
        if (!orgRegInfo.address().equals(orgRegInfo.address().trim())) {
            throw new IllegalArgumentException("Поле 'address' в 'orgRegInfo' не должно содержать пробелы в начале или в конце.");
        }
        if (orgRegInfo.address().isEmpty()) {
            throw new IllegalArgumentException("Поле 'address' в 'orgRegInfo' должно содержать хотя бы 1 символ.");
        }
        if (orgRegInfo.address().length() > 255) {
            throw new IllegalArgumentException("Поле 'address' в 'orgRegInfo' не должно превышать 255 символов.");
        }
        if (orgRegInfo.address().matches("^\\d+$")) {
            throw new IllegalArgumentException("Поле 'address' в 'orgRegInfo' не может состоять только из цифр.");
        }
        if (orgRegInfo.address().matches("^[^\\p{L}\\p{N}]+$")) {
            throw new IllegalArgumentException("Поле 'address' в 'orgRegInfo' не может состоять только из специальных символов.");
        }

        //Проверка iinBin
        if (orgRegInfo.iinBin() == null) {
            throw new IllegalArgumentException("Поле 'iinBin' в 'orgRegInfo' не может быть null.");
        }
        if (orgRegInfo.iinBin().length() != 12) {
            throw new IllegalArgumentException("Поле 'iinBin' в 'orgRegInfo' должно содержать ровно 12 символов.");
        }
        if (!orgRegInfo.iinBin().matches("^\\d{12}$")) {
            throw new IllegalArgumentException("Поле 'iinBin' в 'orgRegInfo' должно состоять только из цифр.");
        }

        //Проверка oked
        if (orgRegInfo.oked() == null) {
            throw new IllegalArgumentException("Поле 'oked' в 'orgRegInfo' не может быть null.");
        }
        if (orgRegInfo.oked().isEmpty()) {
            throw new IllegalArgumentException("Поле 'oked' в 'orgRegInfo' должно содержать хотя бы 1 символ.");
        }
        if (orgRegInfo.oked().length() > 255) {
            throw new IllegalArgumentException("Поле 'oked' в 'orgRegInfo' не должно превышать 255 символов.");
        }
        if (!orgRegInfo.oked().matches("^\\d+$")) {
            throw new IllegalArgumentException("Поле 'oked' в 'orgRegInfo' должно состоять только из цифр.");
        }

        return Reginfo.OrgRegInfo.newBuilder()
                .setTitle(orgRegInfo.title())
                .setAddress(orgRegInfo.address())
                .setInn(orgRegInfo.iinBin())
                .setOkved(orgRegInfo.oked())
                .build();
    }

    /**
     * Метод для создания и возвращения структуры 'OfflinePeriod' в формате протокола
     * */
    private static Service.ServiceRequest.OfflinePeriod createProtoOfflinePeriod(OfflinePeriodRequest offlinePeriod) {
        if (offlinePeriod == null) {
            throw new IllegalArgumentException("Объект 'OfflinePeriodRequest' не может быть null.");
        }

        LocalDateTime beginDateTime = offlinePeriod.beginDateTime();
        LocalDateTime endDateTime = offlinePeriod.endDateTime();

        if (beginDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("В 'OfflinePeriodRequest' поле 'beginDateTime' должно быть раньше или равно 'endDateTime'.");
        }

        return Service.ServiceRequest.OfflinePeriod.newBuilder()
                .setBeginTime(DateTimeMapper.toProtoDateTime(beginDateTime))
                .setEndTime(DateTimeMapper.toProtoDateTime(endDateTime))
                .build();
    }
}
