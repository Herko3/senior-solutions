package mathematician;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MathematicianService {

    private List<Mathematician> mathematicians = new ArrayList<>();

    private AtomicLong idGen = new AtomicLong();

    private ModelMapper modelMapper;

    public MathematicianService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MathematicianDTO saveMathematician(CreateMathematicianCommand command) {
        Mathematician mathematician = new Mathematician(idGen.incrementAndGet(), command.getName(), command.getFavouriteTopics(), command.getBirthDate(), command.getFavouritePrim());
        mathematicians.add(mathematician);
        return modelMapper.map(mathematician, MathematicianDTO.class);
    }

    public List<MathematicianDTO> listMathematicians() {
        return mathematicians.stream()
                .map(m->modelMapper.map(m,MathematicianDTO.class))
                .toList();
    }

    public void deleteAll() {
        mathematicians.clear();
        idGen = new AtomicLong();
    }
}
