package game.fighter;

import game.fighter.Fighter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FighterService {

    private List<Fighter> fighters = new ArrayList<>();

    private AtomicLong idGen = new AtomicLong(0);

    private ModelMapper mapper;

    public FighterService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public FighterDto findById(long id) {
        Fighter fighter = findFighterById(id);
        return mapper.map(fighter, FighterDto.class);
    }

    public FighterDto saveFighter(CreateFighterCommand command) {
        Fighter fighter = new Fighter(idGen.incrementAndGet(), command.getName(), command.getHealthPoint(), command.getDamage());
        fighters.add(fighter);
        return mapper.map(fighter, FighterDto.class);
    }

    public List<FighterDto> listFighters() {
        return fighters.stream()
                .map(f -> mapper.map(f, FighterDto.class))
                .toList();
    }

    public FighterDto updateFighter(long id, UpdateFighterCommand command) {
        Fighter fighter = findFighterById(id);
        fighter.setName(command.getName());
        fighter.setDamage(command.getDamage());
        fighter.setHealthPoint(command.getHealthPoint());

        return mapper.map(fighter, FighterDto.class);
    }

    public void deleteByID(long id) {
        fighters.remove(findFighterById(id));
    }

    public void deleteAll() {
        fighters.clear();
        idGen = new AtomicLong(0);
    }

    public Fighter findFighterById(long id) {
        return fighters.stream()
                .filter(f -> f.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoFighterFoundException(id));
    }

}
