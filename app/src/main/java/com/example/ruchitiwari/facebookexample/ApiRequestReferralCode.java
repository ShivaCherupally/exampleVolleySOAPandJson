package com.example.ruchitiwari.facebookexample;

/**
 * Created by RuchiTiwari on 4/14/2017.
 */

public class ApiRequestReferralCode
{
	private int code;

	ApiRequestReferralCode(int code)
	{
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}

	@Override
	public String toString()
	{
		return code + "";
	}
}
