package com.mybrain.kkmlib.internal.models;

/**
 * @param appCode UInt16 → int
 * @param version UInt16 → int
 * @param size    UInt32 → long
 * @param id      UInt32 → long
 * @param token   UInt32 → long
 * @param reqNum  UInt16 → int
 */
public record MessageHeader(int appCode, int version, long size, long id, long token, int reqNum) { }