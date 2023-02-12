package at.ac.fhstp.awp_bad.groupxx.pgmon.util;

import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.PokemonType;
import at.ac.fhstp.awp_bad.groupxx.pgmon.entities.Species;
import at.ac.fhstp.awp_bad.groupxx.pgmon.persistence.SpeciesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PokemonDataImporter {
    private static final Logger logger = LoggerFactory.getLogger(PokemonDataImporter.class);
    private final SpeciesRepository speciesRepository;

    private static final String POKEMON = "pokemon.csv";
    private static final String POKEMON_SPECIES_NAMES = "pokemon_species_names.csv";
    private static final String POKEMON_TYPES = "pokemon_types.csv";
    private static final String POKEMON_TYPE_NAMES = "type_names.csv";

    private static final Integer LANG_EN = 9;
    private static final Integer LANG_DE = 6;

    private final ClassLoader classLoader = PokemonDataImporter.class.getClassLoader();

    public PokemonDataImporter(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
        logger.info(" constructor ...");
    }

    private String getName(Integer speciesId, Integer speciesLang) {
        String name = "";
        String line;
        //logger.info("ImportData from pokemon_species_names.csv ...");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader((Objects.requireNonNull(classLoader.getResourceAsStream(POKEMON_SPECIES_NAMES)))));
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] lineValues = line.split(",");

                if ((Integer.parseInt(lineValues[0]) == speciesId) && (Integer.parseInt(lineValues[1]) == speciesLang)) {
                    name = lineValues[2];
                    break;
                }
            }
            reader.close();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
        return name;
    }

    private List<PokemonType> getTypes(Integer speciesId) {
        List<PokemonType> types = new ArrayList<>();
        String lineTypes;
        Integer typeId;

        BufferedReader readerTypes = new BufferedReader(
                new InputStreamReader((Objects.requireNonNull(classLoader.getResourceAsStream(POKEMON_TYPES)))));
        try {
            readerTypes.readLine();
            while ((lineTypes = readerTypes.readLine()) != null) {
                String[] lineValuesTypes = lineTypes.split(",");
                if (Integer.parseInt(lineValuesTypes[0]) == speciesId) {
                    typeId = Integer.parseInt(lineValuesTypes[1]);
                    PokemonType type = new PokemonType();
                    type.setTypeId(typeId);
                    type.setTypeName_de(getTypeName(typeId, LANG_DE));
                    type.setTypeName_en(getTypeName(typeId, LANG_EN));
                    types.add(type);
                }
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
        return types;
    }

    private String getTypeName(Integer typeId, Integer lang) {
        String lineNames = "";
        BufferedReader readerNames = new BufferedReader(
                new InputStreamReader((Objects.requireNonNull(classLoader.getResourceAsStream(POKEMON_TYPE_NAMES)))));
        String line;
        try {
            readerNames.readLine();
            while ((line = readerNames.readLine()) != null) {
                String[] lineValues = line.split(",");
                if (Integer.parseInt(lineValues[0]) == typeId && Integer.parseInt(lineValues[1]) == lang) {
                    lineNames = lineValues[2];
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return lineNames;
    }

    @PostConstruct
    public void importData() {
        logger.info("ImportData ...");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader((Objects.requireNonNull(classLoader.getResourceAsStream(POKEMON)))));
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] lineValues = line.split(",");
                Integer speciesId;
                Integer height;
                Integer weight;
                String name_de;
                String name_en;
                List<PokemonType> types;

                speciesId = Integer.parseInt(lineValues[2]);
                height = Integer.parseInt(lineValues[3]);
                weight = Integer.parseInt(lineValues[4]);
                name_de = getName(speciesId, LANG_DE);
                name_en = getName(speciesId, LANG_EN);
                types = getTypes(speciesId);
                Species species = new Species();
                species.setHeight(height);
                species.setName_de(name_de);
                species.setName_en(name_en);
                species.setSpeciesId(speciesId);
                species.setWeight(weight);
                species.setPokemonTypes(types);

                speciesRepository.save(species);
            }
            reader.close();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }
}
