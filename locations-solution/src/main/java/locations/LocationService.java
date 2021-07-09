package locations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LocationService {

    public List<Location> locationsFromFile(Path path) {
        try {
            List<String> fileContent = Files.readAllLines(path);

            return fileContent.stream()
                    .map(s -> new LocationParser().parse(s))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file", e);
        }

    }

    public void writeLocations(Path file, List<Location> locations) {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(Files.newBufferedWriter(file)))){
            for(Location location : locations) {
                writer.println(location.getName() + "," + location.getLat() + "," + location.getLon());
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot write file" + ioe);
        }
    }

}
