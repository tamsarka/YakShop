package com.yakyak.UserStory2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.yakyak.UserStory2.constant.YakConstant;
import com.yakyak.UserStory2.data.Herd;
import com.yakyak.UserStory2.data.LabYak;
import com.yakyak.UserStory2.dto.HerdDto;
import com.yakyak.UserStory2.dto.HerdResponse;
import com.yakyak.UserStory2.dto.StockDto;

@Service
public class YakService {
	boolean isFirstTimeShaved = false;

	public HerdResponse getHerdData(Herd myHerd, int day) {
		HerdResponse response = new HerdResponse();
		List<HerdDto> herdLidt = new ArrayList<>();

		for (LabYak yak : myHerd.getYakList()) {
			float currentAgeInYear = ((yak.getAge() * YakConstant.DAYS_IN_YEAR) + day) / YakConstant.DAYS_IN_YEAR;
			HerdDto hrdDto = new HerdDto();

			hrdDto.setName(yak.getName());
			hrdDto.setAge(currentAgeInYear);
			hrdDto.setAgeLastShaved(yak.getAge());

			herdLidt.add(hrdDto);
		}
		response.setHerd(herdLidt);

		return response;
	}

	public StockDto getTotalStock(Herd myHeard, int day) {
		StockDto currenctAvailableStock = new StockDto();
		double milkStock = 0.d;
		int skinAvailableTobeShaved = 0;

		for (LabYak yak : myHeard.getYakList()) {
			milkStock += getMilkStockTillDay(yak, day);
			skinAvailableTobeShaved += getSkinStockTillDay(yak, day);
		}
		currenctAvailableStock.setMilk(milkStock);
		currenctAvailableStock.setSkins(skinAvailableTobeShaved);

		return currenctAvailableStock;
	}

	private double getMilkStockTillDay(LabYak yak, int day) {
		int ageInDay = (int) (yak.getAge() * YakConstant.DAYS_IN_YEAR);
		double totalMilkInStock = IntStream.range(0, day)
				.mapToDouble(
						x -> (YakConstant.MAX_MILK_PRODUCTION - ((ageInDay + x) * YakConstant.MILK_AS_PER_AGE_FACTOR)))
				.sum();

		return totalMilkInStock;
	}

	private int getSkinStockTillDay(LabYak yak, int day) {
		int ageInDay = (int) (yak.getAge() * YakConstant.DAYS_IN_YEAR);
		int totalAvailableSkinTobeSHaved = 0;
		isFirstTimeShaved = false;
		int totalSkinInStock = IntStream.range(0, day).filter(x -> shouldShaveToday(ageInDay + x, x))
				.map(x -> totalAvailableSkinTobeSHaved + 1).sum();
		return totalSkinInStock;
	}

	private boolean shouldShaveToday(int currentAge, int day) {
		if (!isFirstTimeShaved) {
			isFirstTimeShaved = true;
			return true;
		} else {
			return day > (YakConstant.MIN_SHAVED_DAY + (currentAge * YakConstant.SKIN_AS_PER_AGE_FACTOR));
		}

	}
}
