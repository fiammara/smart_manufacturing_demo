package com.manufacture.emailservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertRequestDto {

    private String text;

    private String submit_date;

    public String companyid;

    public long lastFormToEvaluateId;
}
