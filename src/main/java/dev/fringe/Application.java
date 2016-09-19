package dev.fringe;

import dev.fringe.domain.Hotel;
import dev.fringe.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private HotelMapper hotelMapper;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) throws Exception {
        Hotel hotel = new Hotel(new Long((2)));
        hotel.setAddress("add");
        hotel.setZip("ulsan");
        hotel.setName("name");
        this.hotelMapper.insertHotel(hotel);
        System.out.println(this.hotelMapper.selectByCityId(new Hotel(new Long(2))));
    }

}