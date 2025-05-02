package com.mybrain.kkmlib.api.request.models;

import com.mybrain.kkmlib.api.models.reginfo.KkmRegInfo;
import com.mybrain.kkmlib.api.models.reginfo.OrgRegInfo;

public record RegInfoRequest(KkmRegInfo kkm, OrgRegInfo org) { }
