package locations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class LocationService {

    public List<Location> locationsFromFile(Path path){
        try {
            List<String> fileContent = Files.readAllLines(path);

            return fileContent.stream()
                    .map(s -> new LocationParser().parse(s))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file", e);
        }

    }

}
