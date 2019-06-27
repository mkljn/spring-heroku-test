package ip.consumer.client;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
public class GeoDto {
	
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//private Integer id;
	private String Ip;
	private String Country;
	private String State;
	private String City;
	private String ZipCode;
	
	public GeoDto(String ip, String country, String state, String city, String zipCode) {
		Ip = ip;
		Country = country;
		State = state;
		City = city;	
		ZipCode = zipCode;
	}
	

	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	
	
	
}
