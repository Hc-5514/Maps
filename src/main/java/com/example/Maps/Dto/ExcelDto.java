package com.example.Maps.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ExcelDto {

    private Integer date;

    private String time;

    private String spot;

    private Double temp;

    private Double wind;

    private Integer humidity;

    private String rain;

    private String selectHour;

    private String selectMinute;
}
