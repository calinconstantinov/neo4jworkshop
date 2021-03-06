package com.endava.neo4jworkshop.service;

import com.endava.neo4jworkshop.model.time.Day;
import com.endava.neo4jworkshop.model.time.Hour;
import com.endava.neo4jworkshop.model.time.Month;
import com.endava.neo4jworkshop.model.time.Year;
import com.endava.neo4jworkshop.repository.time.DayRepository;
import com.endava.neo4jworkshop.repository.time.HourRepository;
import com.endava.neo4jworkshop.repository.time.MonthRepository;
import com.endava.neo4jworkshop.repository.time.YearRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class TimeService {

    private static final String HOUR_FORMAT = "%d%02d%02d%02d";
    private static final String DAY_FORMAT = "%d%02d%02d";
    private static final String MONTH_FORMAT = "%d%02d";
    private static final String YEAR_FORMAT = "%d";

    YearRepository yearRepository;

    MonthRepository monthRepository;

    DayRepository dayRepository;

    HourRepository hourRepository;

    public Year find(int year) {
        return yearRepository.findByUuid(format(YEAR_FORMAT, year));
    }

    public Month find(int year, int month) {
        return monthRepository.findByUuid(format(MONTH_FORMAT, year, month));
    }

    public Day find(int year, int month, int day) {
        return dayRepository.findByUuid(format(DAY_FORMAT, year, month, day));
    }

    public Hour find(int year, int month, int day, int hour) {
        return hourRepository.findByUuid(format(HOUR_FORMAT, year, month, day, hour));
    }
}
