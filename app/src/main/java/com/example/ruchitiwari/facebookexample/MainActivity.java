package com.example.ruchitiwari.facebookexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity
{
	private Map<String, String> mParams;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final TextView mTextView = (TextView) findViewById(R.id.text);
		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(this);
		String url = "http://104.211.96.182:91/Service.asmx/Authenticate_User";

		mParams = new HashMap<String, String>();
		mParams.put("ptntcode", "SHRAF2510882");
		mParams.put("pwd", "SHRA");

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
		                                                new Response.Listener<String>()
		                                                {
			                                                @Override
			                                                public void onResponse(String response)
			                                                {
				                                                response = response.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
				                                                response = response.replace("<string xmlns=\"http://tempuri.org/\">", "");
				                                                response = response.replace("<string xmlns=\"http://220.227.57.139:81/\">", "");
				                                                response = response.replace("</string>", "");
				                                                Log.e("response", response + "");
				                                                try
				                                                {
					                                                JSONObject jobj = new JSONObject(response);
					                                                String code = jobj.getString("code");
					                                                Log.e("code", code + "");
					                                                String msg = jobj.getString("msg");
					                                                Log.e("msg", msg + "");
					                                                JSONArray jsonArray = jobj.getJSONArray("data");
					                                                Log.e("jsonArray", jsonArray + "");
					                                                for (int i = 0; i < jsonArray.length(); i++)
					                                                {
						                                                String userId = jsonArray.getJSONObject(i).getString("PTNT_CD");
						                                                Log.e("userId", userId + "");
						                                                String emailid = jsonArray.getJSONObject(i).getString("EMAIL_ID");
						                                                Log.e("emailid", emailid + "");
						                                                String city = jsonArray.getJSONObject(i).getString("CITY");
						                                                Log.e("city", city + "");
					                                                }


//
//
//						OffersListItemData offerlist_data = new OffersListItemData();
//						offerlist_data.setID(jArray.getJSONObject(i).getInt(Constants.offer_id));
//						offerlist_data.setDESCRIPTION(jArray.getJSONObject(i).getString(Constants.offer_description));
//						offerlist_data.setIMAGE_URL(jArray.getJSONObject(i).getString(Constants.offer_image_url));
//						offerlist_data.setCREATED_DATE(jArray.getJSONObject(i).getString(Constants.offer_created_date));
//						offerlist_data.setACTIVE_FLAG(jArray.getJSONObject(i).getString(Constants.offer_active_date));
//						offerlist_data.setNAME(jArray.getJSONObject(i).getString(Constants.offer_name));
//						offerlist_data.setFRM_DT(jArray.getJSONObject(i).getString(Constants.offer_from_date));
//						offerlist_data.setTO_DT(jArray.getJSONObject(i).getLong(Constants.offer_to_date));
//						offerlist_data.setSEQUENCE(jArray.getJSONObject(i).getString(Constants.offer_sequence));
//						offerlist_data.setTESTCODE(jArray.getJSONObject(i).getString(Constants.offer_test_code));
//						offerlist_data.setDISCOUNT_TYPE(jArray.getJSONObject(i).getString(Constants.offer_discount_type));
//						offerlist_data.setPERC_AMT(jArray.getJSONObject(i).getString(Constants.offer_perc_amt));
//						offerlist_data.setACTION(jArray.getJSONObject(i).getString(Constants.offer_action));
//						offerlist_data.setBANNER_FLG(jArray.getJSONObject(i).getString(Constants.offer_banner_flag));
//						if (jArray.getJSONObject(i).getString(Constants.offer_banner_flag).equalsIgnoreCase("N"))
//						{
//							mOffersListItemData.add(offerlist_data);
//						}
//
//					}
				                                                }
				                                                catch (Exception e)
				                                                {
					                                                e.printStackTrace();
				                                                }

				                                                // Display the first 500 characters of the response string.
				                                                mTextView.setText("Response is: " + response);
			                                                }
		                                                }, new Response.ErrorListener()


		{
			@Override
			public void onErrorResponse(VolleyError error)
			{
				mTextView.setText("That didn't work!");
			}
		})
		{
			@Override
			protected Map<String, String> getParams()
			{
				return mParams;
			}
		};

// Add the request to the RequestQueue.
		queue.add(stringRequest);
	}




	/*StringRequest req = new StringRequest(Request.Method.POST, url,
	                                      new Response.Listener<String>() {
		                                      @Override
		                                      public void onResponse(String response) {
			                                      response = response.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
			                                      response = response.replace("<string xmlns=\"http://tempuri.org/\">", "");
			                                      response = response.replace("<string xmlns=\"http://220.227.57.139:81/\">", "");

			                                      response = response.replace("</string>", "");

			                                      Log.e("response", response + "");
			                                      JSONObject jObj = null;

			                                      hideProgressDialog();

			                                      try {
				                                      try {
					                                      jObj = new JSONObject(response);
				                                      } catch (Exception e) {

				                                      }

				                                      HashMap<String, String> value = new HashMap<String, String>();
				                                      String responsetoActivity = "";

				                                      switch (api) {

					                                      case PENDING_REGISTRATION:

						                                      if (jObj != null) {
							                                      responsetoActivity = jObj.getString(Constants.fieldGetResponse);
							                                      Log.e("createobj", responsetoActivity + "");
							                                      if (responsetoActivity != null && responsetoActivity.equalsIgnoreCase("Pending Registration")) {

								                                      if (!jObj.isNull("data") && jObj.getJSONArray("data") != null) {
									                                      JSONArray jArr = jObj.getJSONArray("data");
									                                      Log.e("jArr", jArr + "");
									                                      value.put(Constants.registered_mobile, jArr.getJSONObject(0).getString("MOBILE_NO"));
								                                      }
								                                      Log.e("value", value + "");
							                                      }
						                                      }
						                                      break;
					                                      case USER_REGISTRATION:
						                                      if (jObj != null) {
							                                      responsetoActivity = jObj.getString(Constants.fieldGetResponse);
						                                      }
						                                      break;
					                                      case RESEND_OTP:

						                                      responsetoActivity = jObj.getString(Constants.fieldGetResponse);
						                                      break;
					                                      case VALIDATE_REGISTRATION:

						                                      String strobj = "";
						                                      try {
							                                      strobj = jObj.getString(Constants.fieldGetResponse);
						                                      } catch (Exception e) {

						                                      }
						                                      if (strobj != null) {
							                                      responsetoActivity = strobj;
						                                      } else {
							                                      responsetoActivity = "invalid";
						                                      }
						                                      break;

					                                      case LOGIN:

						                                      if (jObj != null) {
							                                      responsetoActivity = jObj.getString(Constants.fieldGetResponse);
						                                      }
						                                      if (responsetoActivity != null && !responsetoActivity.isEmpty() && responsetoActivity
								                                      .equalsIgnoreCase("Query Successful")) {
							                                      JSONArray jArr = jObj.getJSONArray("data");

							                                      ///User Details
							                                      value.put(Constants.jsonFieldUserID, jArr.getJSONObject(0).getString(Constants.jsonFieldUserID));
							                                      value.put(Constants.jsonFieldPtnt_cd, jArr.getJSONObject(0).getString(Constants.jsonFieldPtnt_cd));
							                                      value.put(Constants.jsonFieldPtnt_title,
							                                                jArr.getJSONObject(0).getString(Constants.jsonFieldPtnt_title));
							                                      value.put(Constants.jsonFieldFirstName,
							                                                jArr.getJSONObject(0).getString(Constants.jsonFieldFirstName));
							                                      value.put(Constants.jsonFieldLastName, jArr.getJSONObject(0).getString(Constants.jsonFieldLastName));

							                                      value.put(Constants.jsonFieldGender, jArr.getJSONObject(0).getString(Constants.jsonFieldGender));
							                                      value.put(Constants.jsonFieldDob, jArr.getJSONObject(0).getLong(Constants.jsonFieldDob) + "");
							                                      value.put(Constants.jsonFieldmartial_status,
							                                                jArr.getJSONObject(0).getString(Constants.jsonFieldmartial_status));
							                                      value.put(Constants.jsonFieldEmail, jArr.getJSONObject(0).getString(Constants.jsonFieldEmail));
							                                      value.put(Constants.jsonFieldZip, jArr.getJSONObject(0).getString(Constants.jsonFieldZip));
							                                      value.put(Constants.jsonFieldMOBILE, jArr.getJSONObject(0).getString(Constants.jsonFieldMOBILE));
							                                      value.put(Constants.jsonFieldAddress1, jArr.getJSONObject(0).getString(Constants.jsonFieldAddress1));
							                                      value.put(Constants.jsonFieldAddress2, jArr.getJSONObject(0).getString(Constants.jsonFieldAddress2));
							                                      value.put(Constants.jsonFieldlandmark, jArr.getJSONObject(0).getString(Constants.jsonFieldlandmark));
							                                      value.put(Constants.jsonFieldcity, jArr.getJSONObject(0).getString(Constants.jsonFieldcity));
							                                      value.put(Constants.jsonFieldstate, jArr.getJSONObject(0).getString(Constants.jsonFieldstate));
							                                      value.put(Constants.jsonFieldMcountry, jArr.getJSONObject(0).getString(Constants.jsonFieldMcountry));
							                                      value.put(Constants.jsonFieldparentid, jArr.getJSONObject(0).getString(Constants.jsonFieldparentid));
							                                      if (!Util.getStoredUsername(context).isEmpty()) {
								                                      value.put(Constants.STATUS, "true");
							                                      } else {
								                                      value.put(Constants.STATUS, "false");
							                                      }

						                                      }


						                                      break;

					                                      case USER:
						                                      String msg = "";
						                                      if (jObj != null) {
							                                      msg = responsetoActivity = jObj.getString(Constants.fieldGetResponse);
						                                      }
						                                      if (msg != null && !msg.isEmpty() && msg.equalsIgnoreCase("Query Successful")) {
							                                      JSONArray jArr = jObj.getJSONArray("data");
							                                      value.put(Constants.jsonFieldMOBILE, jArr.getJSONObject(0).getString(Constants.jsonFieldMOBILE));
							                                      value.put(Constants.jsonFieldEmail, jArr.getJSONObject(0).getString(Constants.jsonFieldEmail));
						                                      }
						                                      break;

					                                      case FORGOT_PWD:
						                                      if (jObj != null) {
							                                      responsetoActivity = jObj.getString(Constants.fieldGetResponse);
						                                      }
						                                      break;
				                                      }


				                                      if (responsetoActivity != null && !responsetoActivity.isEmpty()) {
					                                      ((VolleyCallback) context).onSuccessResponse(api, responsetoActivity, value);
				                                      } else {
					                                      NeResponseError(context);
				                                      }


			                                      } catch (JSONException e) {
				                                      e.printStackTrace();
			                                      }
		                                      }
	                                      },
	                                      new Response.ErrorListener() {
		                                      @Override
		                                      public void onErrorResponse(VolleyError error) {
			                                      hideProgressDialog();
			                                      NeResponseError(context);

		                                      }
	                                      }) {
		@Override
		protected Map<String, String> getParams() {

			return passedparams;
		}

	};*/


}
