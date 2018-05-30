package com.srh_heidelberg.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateCalculations {



        public static Date addDay(Date date, int i) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_YEAR, i);
            return cal.getTime();
        }
        public static Date addMonth(Date date, int i) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, i);
            return cal.getTime();
        }
        public static Date addYear(Date date, int i) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR, i);
            return cal.getTime();
        }

        public static Date stringToDateParse (String ipDate) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date IPdate = null;
            try {
                IPdate = format.parse(ipDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return IPdate;
        }

        }


