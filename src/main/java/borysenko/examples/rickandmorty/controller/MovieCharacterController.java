package borysenko.examples.rickandmorty.controller;

import borysenko.examples.rickandmorty.dto.character.MovieCharacterResponseDto;
import borysenko.examples.rickandmorty.service.MovieCharacterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@RestController
@RequestMapping("/movie_characters")
public class MovieCharacterController {
    private final MovieCharacterService service;

    public MovieCharacterController(MovieCharacterService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping
    public List<MovieCharacterResponseDto> getAll(
            @RequestParam(defaultValue = "0")
            @ApiParam(value = "default value is '0'") Integer page,
            @RequestParam(defaultValue = "10")
            @ApiParam(value = "default value is '0'")Integer count,
            @RequestParam(defaultValue = "name")
            @ApiParam(value = "default value is 'name' ") String sortBy) {
        return service.getAll(page, count, sortBy);
    }

    @ApiOperation(value = "Get random character")
    @GetMapping("/randomCharacter")
    public MovieCharacterResponseDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @ApiOperation(value = "Get all characters by name part")
    @GetMapping("/by-name")
    public List<MovieCharacterResponseDto> getByNamePart(
            @RequestParam @ApiParam(name = "namePart", type = "String") String namePart) {
        return service.getByNamePart(namePart);
    }
}
