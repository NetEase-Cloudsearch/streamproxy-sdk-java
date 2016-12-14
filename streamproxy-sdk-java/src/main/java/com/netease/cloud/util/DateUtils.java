package com.netease.cloud.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Utilities for parsing and formatting dates.
 * <p>
 * Note that this class doesn't use static methods because of the synchronization issues with
 * SimpleDateFormat. This lets synchronization be done on a per-object level, instead of on a
 * per-class level.
 */
public class DateUtils {

    // ISO8601日起格式
    private static final DateTimeFormatter iso8601DateFormat = DateTimeFormat.forPattern(
            "yyyy-MM-dd'T'HH:mm:ss.SSS Z").withLocale(Locale.US);

    private static final DateTimeFormatter alternateIso8601DateFormat = DateTimeFormat.forPattern(
            "yyyy-MM-dd'T'HH:mm:ss Z").withLocale(Locale.US);

    // RFC1123 日期格式
    private static final DateTimeFormatter rfc822DateFormat = DateTimeFormat.forPattern(
            "EEE, dd MMM yyyy HH:mm:ss ZZZ").withLocale(Locale.US);

    // RFC1123 日期格式
    private static final SimpleDateFormat rfc1123DateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);

    /** RFC 822 format */
    /**
     * Constructs a new DateUtils object, ready to parse/format dates.
     */
    public DateUtils() {}

    /**
     * Parses the specified date string as an ISO 8601 date and returns the Date object.
     * 
     * @param dateString The date string to parse.
     * @return The parsed Date object.
     * @throws ParseException If the date string could not be parsed.
     */
    public Date parseIso8601Date(String dateString) throws ParseException {
        try {
            return new Date(iso8601DateFormat.parseMillis(dateString));
        } catch (Exception e) {
            return new Date(alternateIso8601DateFormat.parseMillis(dateString));
        }
    }

    /**
     * Formats the specified date as an ISO 8601 string.
     * 
     * @param date The date to format.
     * @return The ISO 8601 string representing the specified date.
     */
    public String formatIso8601Date(Date date) {
        return iso8601DateFormat.print(date.getTime());
    }

    /**
     * Parses the specified date string as an RFC 822 date and returns the Date object.
     * 
     * @param dateString The date string to parse.
     * @return The parsed Date object.
     * @throws ParseException If the date string could not be parsed.
     */
    public Date parseRfc822Date(String dateString) throws ParseException {
        long mills = rfc822DateFormat.parseMillis(dateString);
        return new Date(mills);
    }

    /**
     * Formats the specified date as an RFC 822 string.
     * 
     * @param date The date to format.
     * @return The RFC 822 string representing the specified date.
     */
    public String formatRfc822Date(Date date) {
        return rfc822DateFormat.print(date.getTime());
    }

    /**
     * Formats the specified date as an RFC 1123 string.
     * 
     * @param date The date to format.
     * @return The RFC 822 string representing the specified date.
     */
    public String formatRfc1123Date(Date date) {
        rfc1123DateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String rfc1123Datestr = rfc1123DateFormat.format(date);
        return rfc1123Datestr.substring(0, rfc1123Datestr.indexOf("+"));
    }

}
