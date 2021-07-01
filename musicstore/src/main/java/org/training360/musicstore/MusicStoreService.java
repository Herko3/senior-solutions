package org.training360.musicstore;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MusicStoreService {

    private List<Instrument> instruments = new ArrayList<>();

    private AtomicLong idGenerator = new AtomicLong();
    private ModelMapper mapper;

    public MusicStoreService(ModelMapper mapper) {
        this.mapper = mapper;
    }


    public List<InstrumentDTO> getInstruments(Optional<String> brand, Optional<Long> price) {
        return instruments.stream()
                .filter(i -> brand.isEmpty() || i.getBrand().toLowerCase().contains(brand.get().toLowerCase()))
                .filter(i-> price.isEmpty() || i.getPrice() == price.get())
                .map(i -> mapper.map(i, InstrumentDTO.class))
                .toList();
    }

    public InstrumentDTO createInstrument(CreateInstrumentCommand command) {
        Instrument instrument = new Instrument(idGenerator.incrementAndGet(), command.getBrand(), command.getInstrumentType(), command.getPrice(), LocalDate.now());
        instruments.add(instrument);

        return mapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteInstruments() {
        instruments.clear();
        idGenerator = new AtomicLong();
    }

    public InstrumentDTO getInstrumentById(long id) {
        Instrument instrument = getInstrument(id);

        return mapper.map(instrument,InstrumentDTO.class);
    }

    private Instrument getInstrument(long id){
        return instruments.stream()
                .filter(i->i.getId() == id)
                .findAny()
                .orElseThrow(()-> new InstrumentNotFound("No Instrument with id: " +id));
    }

    public InstrumentDTO updatePrice(long id, UpdatePriceCommand command) {
        Instrument instrument = getInstrument(id);
        if(instrument.getPrice() != command.getPrice()){
            instrument.setPrice(command.getPrice());
            instrument.setPostDate(LocalDate.now());
        }
        return mapper.map(instrument,InstrumentDTO.class);
    }

    public void deleteInstrumentById(long id) {
        Instrument instrument = getInstrument(id);

        instruments.remove(instrument);
    }
}
