package bicyclesharing;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BikeService {

    private ModelMapper modelMapper;
    private List<Bike> bikes = new ArrayList<>();

    public BikeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private void readFromFile() {
        try {
            List<String> lines = Files.readAllLines(Path.of("src/main/resources/bikes.csv"));
            lines.forEach(this::addRide);

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file", ioe);
        }
    }


    public List<BikeDto> getAllRides() {
        cache();

        return bikes.stream()
                .map(r -> modelMapper.map(r, BikeDto.class))
                .toList();
    }

    public List<String> getIds() {
        cache();

        return bikes.stream()
                .map(Bike::getUserId)
                .distinct()
                .toList();
    }

    private void cache() {
        if (bikes.isEmpty()) {
            readFromFile();
        }
    }

    private void addRide(String line) {
        String[] parts = line.split(";");
        String bikeId = parts[0];
        String userId = parts[1];
        double distance = Double.parseDouble(parts[3]);
        LocalDateTime date = parseDate(parts[2]);

        bikes.add(new Bike(bikeId, userId, date, distance));
    }

    private LocalDateTime parseDate(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public List<Bike> getRides() {
        return bikes;
    }
}
