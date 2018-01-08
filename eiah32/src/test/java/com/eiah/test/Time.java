package com.eiah.test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Time {
	public static void main(String[] args) {
		/**
		 * LocalDate是一个不可变的类，它表示默认格式(yyyy-MM-dd)的日期
		 */
		// 获取当前日期（2017-05-31）
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		
		// 指定特定日期(2019-02-22)
		LocalDate currDate = LocalDate.of(2019, Month.FEBRUARY, 22);
		System.out.println(currDate);
		
		// 按指定时区获得日期
		LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
		System.out.println(today);
		
		// 从1970-01-01那天开始加366天后的日期
		LocalDate d1 = LocalDate.ofEpochDay(366L);
		System.out.println(d1);
		
		// 从2000-01-01那天开始加37天后的日期
		LocalDate d2 = LocalDate.ofYearDay(2000, 37);
		System.out.println(d2);
		
		
		System.out.println("--------------------------");
		
		/**
		 * LocalTime是一个不可变的类，默认格式是hh:mm:ss.zzz
		 */
		// 获取当前时间11:54:22.153，纳秒默认取前三位
		LocalTime currTime = LocalTime.now();
		System.out.println(currTime);
		
		// 指定某个时间点12:34:56.999999999
		LocalTime specificTime = LocalTime.of(12, 34, 56, 999999999);
		System.out.println(specificTime);
		
		// 按时区获得时间
		LocalTime sqTime = LocalTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(sqTime);
		
		// 将秒数转化为时间，参数最大值86399即23:59:59
		LocalTime secondOfDay = LocalTime.ofSecondOfDay(86399L);
		System.out.println(secondOfDay);
		
		System.out.println("------------LocalDateTime------------");
		
		/**
		 * LocalDateTime是一个不可变的日期-时间对象，默认格式是yyyy-MM-dd-HH-mm-ss.zzz
		 */
		
		// 获取当前的日期时间（2017-05-31T14:17:44.597）
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		// 获取当前的日期时间（2017-05-31T14:17:44.597）
		LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		System.out.println(localDateTime);
		
		// 获取自定义时间（2019-08-15T23:59:59.000000999）
		LocalDateTime specificDateTime = LocalDateTime.of(2019, Month.AUGUST, 15, 23, 59, 59, 999);
		System.out.println(specificDateTime);
		
		// 获取当前的日期时间（2017-05-31T14:17:44.597）
		LocalDateTime todayKolkata = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(todayKolkata);
		
		// 从1970-01-01那天开始加60秒后的日期时间，且可以指定纳秒，打印结果：1970-01-01T00:01:00.000000999
		LocalDateTime localDateTimeZone = LocalDateTime.ofEpochSecond(60L, 999, ZoneOffset.UTC);
		System.out.println(localDateTimeZone);
		
		/**
		 * java.time.Instant：Instant类是用在机器可读的时间格式上的，它以Unix时间戳的形式存储日期时间.
		 */
		
		// 时间戳(2017-05-31T06:28:40.196Z)
		Instant timestamp = Instant.now();
		System.out.println(timestamp);
		
		// 2017-05-31T06:28:40.196Z
		Instant specificTimes = Instant.ofEpochMilli(timestamp.toEpochMilli());
		System.out.println(specificTimes);
		
		// PT720H
		Duration d = Duration.ofDays(30L);
		System.out.println(d);
		
		System.out.println("-----日期------");
		
		/**
		 * 日期
		 */
		
		// 当前日期(2017-05-31)
		LocalDate lDate = LocalDate.now();
		System.out.println(lDate);
		
		// 2017:false
		System.out.println(lDate.getYear() + ":" + lDate.isLeapYear());
		
		// false
		System.out.println(lDate.isBefore(LocalDate.of(2015, 002, 02)));
		
		// 根据日期创建一个日期时间(2017-05-31T14:46:21.499)
		System.out.println(lDate.atTime(LocalTime.now()));
		
		// 当前日期加3天
		System.out.println(lDate.plusDays(3L));
		// 当前日期加3月
		System.out.println(lDate.plusMonths(3L));
		// 当前日期加3年
		System.out.println(lDate.plusYears(3L));
		// 当前日期加3周
		System.out.println(lDate.plusWeeks(3L));
		
		System.out.println("**************");
		
		// 当前日期减3天
		System.out.println(lDate.minusDays(3L));
		// 当前日期减3月
		System.out.println(lDate.minusMonths(3L));
		// 当前日期减3年
		System.out.println(lDate.minusYears(3L));
		// 当前日期减3周
		System.out.println(lDate.minusWeeks(3L));
		
		System.out.println("$$$$$TemporalAdjusters$$$$");
		// 这个月的第一天的日期(2017-05-01)
		System.out.println(lDate.with(TemporalAdjusters.firstDayOfMonth()));
		// 这个年的最后一天的日期(2017-05-31)
		LocalDate lastDayOfYear = lDate.with(TemporalAdjusters.lastDayOfYear());
		System.out.println(lastDayOfYear);
		
		Period period = lDate.until(lastDayOfYear);
		System.out.println(period + ":" + period.getMonths());
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
		String shijian = LocalDateTime.now().format(dtf);
		System.out.println(shijian);
		
	}
	
}
