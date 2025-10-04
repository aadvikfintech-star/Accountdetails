package com.cooperative.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

public @JsonIgnoreProperties(ignoreUnknown = true)
class ProfileData {
	private String client_id;
	private String name;
	private String mobile;
	
	@JsonProperty("personal_info")
	private PersonalInfo personal_info;
	
	@JsonProperty("identity_info")
	private IdentityInfo identity_info;
	
	@JsonProperty("address_info")
	private List<AddressInfo> address_info;
	
	@JsonProperty("phone_info")
	private List<PhoneInfo> phone_info;
	
	@JsonProperty("email_info")
	private List<EmailAddressInfo> email_info;
	
	

	public ProfileData(String client_id, String name, String mobile) {
		super();
		this.client_id = client_id;
		this.name = name;
		this.mobile = mobile;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public PersonalInfo getPersonal_info() {
		return personal_info;
	}

	public void setPersonal_info(PersonalInfo personal_info) {
		this.personal_info = personal_info;
	}

	public IdentityInfo getIdentity_info() {
		return identity_info;
	}

	public void setIdentity_info(IdentityInfo identity_info) {
		this.identity_info = identity_info;
	}

	public List<AddressInfo> getAddress_info() {
		return address_info;
	}

	public void setAddress_info(List<AddressInfo> address_info) {
		this.address_info = address_info;
	}

	public List<PhoneInfo> getPhone_info() {
		return phone_info;
	}

	public void setPhone_info(List<PhoneInfo> phone_info) {
		this.phone_info = phone_info;
	}

	public List<EmailAddressInfo> getEmail_info() {
		return email_info;
	}

	public void setEmail_info(List<EmailAddressInfo> email_info) {
		this.email_info = email_info;
	}

}

class PersonalInfo {
	@JsonProperty("full_name")
	private String full_name;
	
	@JsonProperty("dob")
	private String dob;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("age")
	private String age;

	@JsonProperty("total_income")
	private String total_income;

	@JsonProperty("occupation")
	private String occupation;

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTotal_income() {
		return total_income;
	}

	public void setTotal_income(String total_income) {
		this.total_income = total_income;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}


	
}



class IdentityInfo {
	@JsonProperty("pan_number")
	private List<PANId> pan_number;

	@JsonProperty("passport_number")
	private List<Passport> passport_number;
	
	@JsonProperty("driving_license")
	private List<DrivingLicense> driving_license;

	@JsonProperty("voter_id")
	private List<VoterId> voter_id;
	
	@JsonProperty("aadhaar_number")
	private List<Aadhar> aadhaar_number;
	
	@JsonProperty("ration_card")
	private List<Ration> ration_card;
	
	@JsonProperty("other_id")
	private List<OtherId> other_id;

	public List<PANId> getPan_number() {
		return pan_number;
	}

	public void setPan_number(List<PANId> pan_number) {
		this.pan_number = pan_number;
	}

	public List<Passport> getPassport_number() {
		return passport_number;
	}

	public void setPassport_number(List<Passport> passport_number) {
		this.passport_number = passport_number;
	}

	public List<DrivingLicense> getDriving_license() {
		return driving_license;
	}

	public void setDriving_license(List<DrivingLicense> driving_license) {
		this.driving_license = driving_license;
	}

	public List<VoterId> getVoter_id() {
		return voter_id;
	}

	public void setVoter_id(List<VoterId> voter_id) {
		this.voter_id = voter_id;
	}

	public List<Aadhar> getAadhaar_number() {
		return aadhaar_number;
	}

	public void setAadhaar_number(List<Aadhar> aadhaar_number) {
		this.aadhaar_number = aadhaar_number;
	}

	public List<Ration> getRation_card() {
		return ration_card;
	}

	public void setRation_card(List<Ration> ration_card) {
		this.ration_card = ration_card;
	}

	public List<OtherId> getOther_id() {
		return other_id;
	}

	public void setOther_id(List<OtherId> other_id) {
		this.other_id = other_id;
	}

}

class Passport {
	@JsonProperty("id_number")
	private String id_number;

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
}
class Aadhar {
	@JsonProperty("id_number")
	private String id_number;

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
}
class Ration {
	@JsonProperty("id_number")
	private String id_number;

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
}
class OtherId {
	@JsonProperty("id_number")
	private String id_number;

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
}
class VoterId {
	@JsonProperty("id_number")
	private String id_number;

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
}
class DrivingLicense {
	@JsonProperty("id_number")
	private String id_number;

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
}

class PANId {
	@JsonProperty("id_number")
	private String id_number;

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
}

class AddressInfo {
	@JsonProperty("reported_date")
	private String reported_date;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("postal")
	private String postal;
	
	@JsonProperty("type")
	private String type;

	public String getReported_date() {
		return reported_date;
	}

	public void setReported_date(String reported_date) {
		this.reported_date = reported_date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

class PhoneInfo {
	
	@JsonProperty("type_code")
	private String type_code;
	
	@JsonProperty("reported_date")
	private String reported_date;
	
	@JsonProperty("number")
	private String number;

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getReported_date() {
		return reported_date;
	}

	public void setReported_date(String reported_date) {
		this.reported_date = reported_date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}

class EmailAddressInfo {
	
	@JsonProperty("reported_date")
	private String reported_date;
	
	@JsonProperty("email_address")
	private String email_address;

	public String getReported_date() {
		return reported_date;
	}

	public void setReported_date(String reported_date) {
		this.reported_date = reported_date;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

}
