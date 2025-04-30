package com.mybrain.kkmlib.api.response.models;

import com.mybrain.kkmlib.api.models.reginfo.KkmRegInfo;
import com.mybrain.kkmlib.api.models.reginfo.OrgRegInfo;
import com.mybrain.kkmlib.api.models.reginfo.PosRegInfo;

import java.util.Optional;

public record RegInfoResponse(KkmRegInfo kkm, Optional<PosRegInfo> pos, OrgRegInfo org) { }
