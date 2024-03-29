package ip.consumer.client;

//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

@RestController
@RequestMapping("/api")
public class GeoController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ResourceLoader resourceLoader;
	private DatabaseReader dbReader;
	
	@GetMapping("/")
	public String sayHi() {
		return "This part is working";
	}

	
	public InputStream dbLoader() throws IOException {
	
	ClassPathResource classPathResource = new ClassPathResource("GeoLite2-City.mmdb");
	InputStream inputStream = classPathResource.getInputStream();
	return inputStream;
	
	}
	
	
	@RequestMapping("/{ipaddress}")
	public List<GeoDto> getDetails(@PathVariable("ipaddress") String ipaddress) throws IOException, GeoIp2Exception {
		
        dbReader = new DatabaseReader.Builder(dbLoader()).build();
        
        InetAddress ipAddress2 = InetAddress.getByName(ipaddress);
        CityResponse response = dbReader.city(ipAddress2);
    	
        String countryName = response.getCountry().getName() != null ? response.getCountry().getName() : "UNKNOWN" ;
        String cityName = response.getCity().getName() != null ? response.getCity().getName() : "UNKNOWN";
        String stateName = response.getLeastSpecificSubdivision().getName() != null ? response.getLeastSpecificSubdivision().getName() : "UNKNOWN";
        String postal = response.getPostal().getCode() != null ? response.getPostal().getCode().toString() : "UNKNOWN";
        dbReader.close();
        userRepository.save(new GeoDto(ipaddress,countryName,stateName, cityName,postal));
	return Collections.singletonList(new GeoDto(ipaddress,countryName,stateName, cityName,postal));	

      
        
	}

}
