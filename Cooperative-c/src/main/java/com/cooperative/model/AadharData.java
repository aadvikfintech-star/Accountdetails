
package com.cooperative.model;

public class AadharData {
	private String client_id;
	private String full_name;
	private String dob;
	private Address address;
	private String age_range;
	private String zip;
	private String profile_image;
	private String aadhaar_number;
	private String state;
	private String gender;
	private String last_digits;
	private String is_mobile;
	private String remarks;
	private String less_info;

	private String pan_number;
	private String category;

	private String input_voter_id;
	private String epic_no;
	// private String gender;
	// private String state;
	private String name;// "PRADIP TUKARAM DEVDHE",
	private String relation_name;// ": "TUKARAM DEVDHE",
	private String relation_type;// FTHR",
	// private String dob;//": null,
	// private String age;//": "28",
	 private String area;//": "Joharapur",
	 private String district;//": "Ahmednagar",
	private String assembly_constituency;// ": "Shevgaon",
	private String polling_station;// "Johrapur Z.P School New Building East West  Bullding From East  Room no. 3",
	private String part_name;// ": "Joharapur",
	private String rln_name_v1;// ": "तुकाराम देवढे",
	private String name_v1;// ": "प्रदीप तुकाराम देवढे",

	private String license_number;
	private String permanent_address;
	private String ola_code;
	private String father_or_husband_name;
	private String doe;
	private String doi;
	
	//pan comprensive
	private String masked_aadhaar;
	private String email;
	private String phone_number;
	
	
	public AadharData(String aadhaar_number, String is_mobile, String remarks) {
		super();
		this.aadhaar_number = aadhaar_number;
		this.is_mobile = is_mobile;
		this.remarks = remarks;
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getAge_range() {
		return age_range;
	}

	public void setAge_range(String age_range) {
		this.age_range = age_range;
	}

	public String getAadhaar_number() {
		return aadhaar_number;
	}

	public void setAadhaar_number(String aadhaar_number) {
		this.aadhaar_number = aadhaar_number;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLast_digits() {
		return last_digits;
	}

	public void setLast_digits(String last_digits) {
		this.last_digits = last_digits;
	}

	public String getIs_mobile() {
		return is_mobile;
	}

	public void setIs_mobile(String is_mobile) {
		this.is_mobile = is_mobile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLess_info() {
		return less_info;
	}

	public void setLess_info(String less_info) {
		this.less_info = less_info;
	}

	public String getPan_number() {
		return pan_number;
	}

	public void setPan_number(String pan_number) {
		this.pan_number = pan_number;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInput_voter_id() {
		return input_voter_id;
	}

	public void setInput_voter_id(String input_voter_id) {
		this.input_voter_id = input_voter_id;
	}

	public String getEpic_no() {
		return epic_no;
	}

	public void setEpic_no(String epic_no) {
		this.epic_no = epic_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation_name() {
		return relation_name;
	}

	public void setRelation_name(String relation_name) {
		this.relation_name = relation_name;
	}

	public String getRelation_type() {
		return relation_type;
	}

	public void setRelation_type(String relation_type) {
		this.relation_type = relation_type;
	}

	public String getAssembly_constituency() {
		return assembly_constituency;
	}

	public void setAssembly_constituency(String assembly_constituency) {
		this.assembly_constituency = assembly_constituency;
	}

	public String getPolling_station() {
		return polling_station;
	}

	public void setPolling_station(String polling_station) {
		this.polling_station = polling_station;
	}

	public String getPart_name() {
		return part_name;
	}

	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}

	public String getRln_name_v1() {
		return rln_name_v1;
	}

	public void setRln_name_v1(String rln_name_v1) {
		this.rln_name_v1 = rln_name_v1;
	}

	public String getName_v1() {
		return name_v1;
	}

	public void setName_v1(String name_v1) {
		this.name_v1 = name_v1;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLicense_number() {
		return license_number;
	}

	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	public String getPermanent_address() {
		return permanent_address;
	}

	public void setPermanent_address(String permanent_address) {
		this.permanent_address = permanent_address;
	}

	public String getOla_code() {
		return ola_code;
	}

	public void setOla_code(String ola_code) {
		this.ola_code = ola_code;
	}

	public String getFather_or_husband_name() {
		return father_or_husband_name;
	}

	public void setFather_or_husband_name(String father_or_husband_name) {
		this.father_or_husband_name = father_or_husband_name;
	}

	public String getDoe() {
		return doe;
	}

	public void setDoe(String doe) {
		this.doe = doe;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getMasked_aadhaar() {
		return masked_aadhaar;
	}

	public void setMasked_aadhaar(String masked_aadhaar) {
		this.masked_aadhaar = masked_aadhaar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}