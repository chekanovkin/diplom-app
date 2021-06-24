package com.example.diplomapp.utils;

import com.example.diplomapp.entities.Task;
import com.example.diplomapp.entities.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TimeUtil {

    private static final double FULLDAY = 480;

    public static double getPercentOfWorkingDay(String time) {

        String[] arr = time.replaceAll("h", "").replaceAll("m", "").split(" ");
        return (Double.parseDouble(arr[0]) * 60 + Double.parseDouble(arr[1])) / FULLDAY;
    }
}
