package com.lee.iweibo.entity.response;

import com.lee.iweibo.entity.Status;

import java.util.ArrayList;


public class StatusTimeLineResponse {

	private ArrayList<Status> statuses;
	private int total_number;

	public ArrayList<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(ArrayList<Status> statuses) {
		this.statuses = statuses;
	}

	public int getTotal_number() {
		return total_number;
	}

	public void setTotal_number(int total_number) {
		this.total_number = total_number;
	}

	@Override
	public String toString() {
		return "StatusTimeLineResponse{" +
				"statuses=" + statuses +
				", total_number=" + total_number +
				'}';
	}
}
