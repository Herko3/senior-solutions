package game.match;

import game.fighter.Fighter;
import game.fighter.FighterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ContestService {

    private FighterService fighterService;

    private List<Match> matches = new ArrayList<>();

    private ModelMapper mapper;

    private AtomicLong idGen = new AtomicLong(0);

    public ContestService(FighterService service, ModelMapper mapper) {
        this.fighterService = service;
        this.mapper = mapper;
    }

    public MatchDto startMatch(CreateMatchCommand command) {
        Fighter fighter = fighterService.findFighterById(command.getFirstId());
        Fighter other = fighterService.findFighterById(command.getSecondId());
        Match match = new Match(idGen.incrementAndGet(), fighter, other);

        matches.add(match);
        return mapper.map(match, MatchDto.class);
    }

    public List<MatchDto> getMatches() {
        return matches.stream()
                .map(m -> mapper.map(m, MatchDto.class))
                .toList();
    }

    public MatchDto findById(long id) {
        Match match = matches.stream()
                .filter(m -> m.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoMatchFoundException(id));

        return mapper.map(match, MatchDto.class);
    }

    public void deleteAll() {
        matches.clear();
        idGen = new AtomicLong(0);
    }
}
